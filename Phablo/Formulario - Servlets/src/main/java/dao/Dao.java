package dao;

import model.Contato;
import model.Endereco;
import model.Formulario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Dao {
    private JPAUtil jpaUtil;

    public Dao(){
        this.jpaUtil = new JPAUtil();
    }

    public void cadastrarFormulario(Formulario f){//Create
        EntityManager entityManager = jpaUtil.getEntityManeger();
        entityManager.getTransaction().begin();
        entityManager.persist(f);
        consertarColunaIdFormularioNotFkDasTabelas(f);
        consertarReferenciaCircularDoFormulario(f);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Formulario> listarFormularios(){//Read
        EntityManager entityManager = jpaUtil.getEntityManeger();
        List<HashMap<String, Object>> resultFormulariosSemEnderecoEContatos = getListaDeHashMapsContendoOsFormulariosSemEnderecoEContatosDoBanco(entityManager);
        List<HashMap<String, Object>> resultEnderecos = getListaDeHashMapsContendoOsEnderecosDoBanco(entityManager);
        List<HashMap<String, Object>> resultContatos = getListaDeHashMapsContendoOsContatosDoBanco(entityManager);

        //criando os formularios(no momento sem endereço e contatos) a partir do resultFormulariosSemEnderecoEContatos
        List<Formulario> formularios = new ArrayList<Formulario>();
        for(int i = 0; i < resultFormulariosSemEnderecoEContatos.size();i++){
            Formulario formulario = getFormularioAPartirDaListaRetornadaPeloCriteria(resultFormulariosSemEnderecoEContatos,i);
            formularios.add(formulario);
        }
        //adicionando os enderecos e os contatos em cada formulario do arraylist
        for(int i=0; i<formularios.size();i++){
            for(int k=0; k<resultContatos.size();k++){
                if(k<resultEnderecos.size()) {
                    if (formularios.get(i).getIdFormulario() == (Integer)resultEnderecos.get(k).get("idFormularioNotFk")) {
                        Endereco endereco = getEnderecoAPartirDaListaRetornadaPeloCriteria(resultEnderecos, k);
                        formularios.get(i).setEndereco(endereco);
                    }
                }
                if(formularios.get(i).getIdFormulario() == (Integer)resultContatos.get(k).get("idFormularioNotFk")){
                    Contato contato = getContatoAPartirDaListaRetornadaPeloCriteria(resultContatos,k);
                    formularios.get(i).getContatos().add(contato);
                }
            }
        }
        entityManager.close();
        return formularios;
    }

    public List<Endereco> listarEnderecos() {
        EntityManager entityManager = jpaUtil.getEntityManeger();
        List<HashMap<String, Object>> result = getListaDeHashMapsContendoOsEnderecosDoBanco(entityManager);
        List<Endereco> enderecos = new ArrayList<Endereco>();
        for (int i=0; i<result.size();i++){
            Endereco endereco = getEnderecoAPartirDaListaRetornadaPeloCriteria(result, i);
            enderecos.add(endereco);
        }
        entityManager.close();
        return enderecos;
    }

    public List<Contato> listarContatos() {
        EntityManager entityManager = jpaUtil.getEntityManeger();
        List<HashMap<String, Object>> result = getListaDeHashMapsContendoOsContatosDoBanco(entityManager);
        List<Contato> contatos = new ArrayList<Contato>();
        for (int i=0; i<result.size();i++){
            Contato contato = getContatoAPartirDaListaRetornadaPeloCriteria(result, i);
            contatos.add(contato);
        }
        entityManager.close();
        return contatos;
    }

    public void editarFormulario(Formulario f) {//update
        EntityManager entityManager = jpaUtil.getEntityManeger();
        entityManager.getTransaction().begin();
        Formulario formularioASerEditado = entityManager.find(Formulario.class, f.getIdFormulario());
        editarOsDadosDoFormularioASerEditado(f,formularioASerEditado);
        consertarColunaIdFormularioNotFkDasTabelas(formularioASerEditado);
        consertarReferenciaCircularDoFormulario(formularioASerEditado);
        entityManager.persist(formularioASerEditado);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private void editarOsDadosDoFormularioASerEditado(Formulario f,Formulario formularioASerEditado){
        formularioASerEditado.setNome(f.getNome());
        formularioASerEditado.setCpf(f.getCpf());
        formularioASerEditado.setEmail(f.getEmail());
        formularioASerEditado.setEscolaridade(f.getEscolaridade());
        formularioASerEditado.getEndereco().setCep(f.getEndereco().getCep());
        formularioASerEditado.getEndereco().setUf(f.getEndereco().getUf());
        formularioASerEditado.getEndereco().setCidade(f.getEndereco().getCidade());
        formularioASerEditado.getEndereco().setBairro(f.getEndereco().getBairro());
        formularioASerEditado.getEndereco().setQuadra(f.getEndereco().getQuadra());;
        formularioASerEditado.getEndereco().setLote(f.getEndereco().getLote());
        formularioASerEditado.getEndereco().setRua(f.getEndereco().getRua());
        formularioASerEditado.getEndereco().setCasa(f.getEndereco().getCasa());
        formularioASerEditado.getEndereco().setNumero(f.getEndereco().getNumero());

        if(formularioASerEditado.getContatos().size() > f.getContatos().size())
            for(int i = formularioASerEditado.getContatos().size() ; i > f.getContatos().size() ; i--)
                formularioASerEditado.getContatos().remove(i-1);

        for(int i = 0 ; i < f.getContatos().size();i++){
            try {
                formularioASerEditado.getContatos().get(i).setNome(f.getContatos().get(i).getNome());
                formularioASerEditado.getContatos().get(i).setEmail(f.getContatos().get(i).getEmail());
                formularioASerEditado.getContatos().get(i).setTelefone(f.getContatos().get(i).getTelefone());
            }catch (IndexOutOfBoundsException exception){
                formularioASerEditado.getContatos().add(new Contato());
                i--;
            }
        }
    }

    public void deletarFormulario(int id){//delete
        EntityManager entityManager = jpaUtil.getEntityManeger();
        entityManager.getTransaction().begin();
        Formulario formularioASerDeletado = entityManager.find(Formulario.class, id);
        entityManager.remove(formularioASerDeletado);
        entityManager.getTransaction().commit();
    }

    private void consertarColunaIdFormularioNotFkDasTabelas(Formulario f){
        f.getEndereco().setIdFormularioNotFk(f.getIdFormulario());
        for(int i = 0; i < f.getContatos().size();i++){
            f.getContatos().get(i).setIdFormularioNotFk(f.getIdFormulario());
        }
    }

    private void consertarReferenciaCircularDoFormulario(Formulario f) {
        f.getEndereco().setFormulario(f);
        for(Contato c : f.getContatos()){
            c.setFormulario(f);
        }
    }



    private static List<HashMap<String, Object>> getListaDeHashMaps(EntityManager entityManager, List<String> colunasDaTabela, Object classe){
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(classe.getClass(),"bean");
        ProjectionList projectionList = Projections.projectionList();
        for (int i=0 ; i< colunasDaTabela.size(); i++){
            String nomeColuna = colunasDaTabela.get(i);
            projectionList.add(Projections.property("bean."+nomeColuna).as(nomeColuna));
        }
        criteria.setProjection(projectionList);
        criteria.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<HashMap<String, Object>> result = criteria.list();
        return result;
    }

    private List<HashMap<String, Object>> getListaDeHashMapsContendoOsFormulariosSemEnderecoEContatosDoBanco(EntityManager entityManager){
        List<String> colunasDaTabelaDeFormulariosSemEnderecoEContatos = new ArrayList<String>(Arrays.asList("idFormulario","nome","email","cpf","escolaridade"));
        Formulario formulario = new Formulario();
        List<HashMap<String, Object>> result = getListaDeHashMaps(entityManager,colunasDaTabelaDeFormulariosSemEnderecoEContatos,formulario);
        return result;
    }

    public static List<HashMap<String, Object>> getListaDeHashMapsContendoOsEnderecosDoBanco(EntityManager entityManager) {
        List<String> colunasDaTabelaDeEndereco = new ArrayList<String>(Arrays.asList("idEndereco","cidade","bairro","rua","quadra","casa","cep","lote","numero","uf","idFormularioNotFk"));
        Endereco endereco = new Endereco();
        List<HashMap<String, Object>> result = getListaDeHashMaps(entityManager,colunasDaTabelaDeEndereco,endereco);
        return result;
    }

    private List<HashMap<String, Object>> getListaDeHashMapsContendoOsContatosDoBanco(EntityManager entityManager) {
        List<String> colunasDaTabelaContatos = new ArrayList<String>(Arrays.asList("idContato", "nome", "telefone", "email", "idFormularioNotFk"));
        Contato contato = new Contato();
        List<HashMap<String, Object>> result = getListaDeHashMaps(entityManager,colunasDaTabelaContatos,contato);
        return result;
    }

    private static Formulario getFormularioAPartirDaListaRetornadaPeloCriteria(List<HashMap<String, Object>> resultFormulariosSemEnderecoEContatos, int i){
        int idFormulario = (Integer) resultFormulariosSemEnderecoEContatos.get(i).get("idFormulario");
        String nome = (String) resultFormulariosSemEnderecoEContatos.get(i).get("nome");
        String email = (String) resultFormulariosSemEnderecoEContatos.get(i).get("email");
        String cpf = (String) resultFormulariosSemEnderecoEContatos.get(i).get("cpf");
        String escolaridade = (String) resultFormulariosSemEnderecoEContatos.get(i).get("escolaridade");
        Formulario formulario = new Formulario(idFormulario,nome,email,cpf,escolaridade);
        formulario.setContatos(new ArrayList<Contato>());
        return formulario;
    }

    private static Endereco getEnderecoAPartirDaListaRetornadaPeloCriteria(List<HashMap<String, Object>> resultEnderecos, int i) {
        int idEndereco = (Integer) resultEnderecos.get(i).get("idEndereco");
        String cidade = (String) resultEnderecos.get(i).get("cidade");
        String bairro = (String) resultEnderecos.get(i).get("bairro");
        String rua = (String) resultEnderecos.get(i).get("rua");
        int quadra = (Integer) resultEnderecos.get(i).get("quadra");
        int casa = (Integer) resultEnderecos.get(i).get("casa");
        String cep = (String) resultEnderecos.get(i).get("cep");
        int lote = (Integer) resultEnderecos.get(i).get("lote");
        int numero = (Integer) resultEnderecos.get(i).get("numero");
        String uf = (String) resultEnderecos.get(i).get("uf");
        Endereco endereco = new Endereco(idEndereco, cidade, bairro, rua, quadra, casa, cep, lote, numero, uf);
        return endereco;
    }

    private static Contato getContatoAPartirDaListaRetornadaPeloCriteria(List<HashMap<String, Object>> resultContatos, int i) {
        int idContato = (Integer) resultContatos.get(i).get("idContato");
        String nomeContato = (String) resultContatos.get(i).get("nome");
        String telefoneContato = (String) resultContatos.get(i).get("telefone");
        String emailContato = (String) resultContatos.get(i).get("email");
        int idFormularioNotFk = (Integer) resultContatos.get(i).get("idFormularioNotFk");
        Contato contato = new Contato(idContato, nomeContato, telefoneContato, emailContato);
        contato.setIdFormularioNotFk(idFormularioNotFk);
        return contato;
    }

}
