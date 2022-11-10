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
import javax.persistence.Query;
import java.util.ArrayList;
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
        entityManager.persist(f); //Assegura que f está no modo maneged
        consertarColunaIdFormularioNotFkDasTabelas(f);
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

    public Endereco listarOEnderecoDeUmFormulario(Formulario f){//Read
        EntityManager entityManager = jpaUtil.getEntityManeger();
        String jpql = "SELECT e from Endereco as e WHERE idFormulario = :idFormulario";
        Query q = entityManager.createQuery(jpql);
        q.setParameter("idFormulario",f.getIdFormulario());
        Endereco endereco = (Endereco) q.getResultList().get(0);
        entityManager.close();
        return endereco;
    }

    public List<Contato> listarContatosDeUmFormulario(Formulario f){//Read
        EntityManager entityManager = jpaUtil.getEntityManeger();
        String jpql = "SELECT c from Contato as c WHERE idFormulario = :idFormulario";
        Query q = entityManager.createQuery(jpql);
        q.setParameter("idFormulario",f.getIdFormulario());
        List<Contato> contatoList = q.getResultList();
        entityManager.close();
        return contatoList;
    }

    public void atualizarFormulario(Formulario f) {//update
        EntityManager entityManager = jpaUtil.getEntityManeger();
        entityManager.getTransaction().begin();
        entityManager.persist(f);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deletarFormulario(Formulario formulario){//delete
        EntityManager entityManager = jpaUtil.getEntityManeger();
        entityManager.getTransaction().begin();
        Formulario formularioASerDeletado = entityManager.find(Formulario.class, formulario.getIdFormulario());
        entityManager.remove(formularioASerDeletado);
        entityManager.getTransaction().commit();
    }

    private void consertarColunaIdFormularioNotFkDasTabelas(Formulario f){
        f.getEndereco().setIdFormularioNotFk(f.getIdFormulario());
        for(int i = 0; i < f.getContatos().size();i++){
            f.getContatos().get(i).setIdFormularioNotFk(f.getIdFormulario());
        }
    }



    private List<HashMap<String, Object>> getListaDeHashMapsContendoOsFormulariosSemEnderecoEContatosDoBanco(EntityManager entityManager){
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Formulario.class,"bean");
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("bean.idFormulario").as("idFormulario"));
        projectionList.add(Projections.property("bean.nome").as("nome"));
        projectionList.add(Projections.property("bean.email").as("email"));
        projectionList.add(Projections.property("bean.cpf").as("cpf"));
        projectionList.add(Projections.property("bean.escolaridade").as("escolaridade"));
        criteria.setProjection(projectionList);
        criteria.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<HashMap<String, Object>> result = criteria.list();
        return result;
    }

    public static List<HashMap<String, Object>> getListaDeHashMapsContendoOsEnderecosDoBanco(EntityManager entityManager) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Endereco.class,"bean");
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("bean.idEndereco").as("idEndereco"));
        projectionList.add(Projections.property("bean.cidade").as("cidade"));
        projectionList.add(Projections.property("bean.bairro").as("bairro"));
        projectionList.add(Projections.property("bean.rua").as("rua"));
        projectionList.add(Projections.property("bean.quadra").as("quadra"));
        projectionList.add(Projections.property("bean.casa").as("casa"));
        projectionList.add(Projections.property("bean.cep").as("cep"));
        projectionList.add(Projections.property("bean.lote").as("lote"));
        projectionList.add(Projections.property("bean.numero").as("numero"));
        projectionList.add(Projections.property("bean.uf").as("uf"));
        projectionList.add(Projections.property("bean.idFormularioNotFk").as("idFormularioNotFk"));
        criteria.setProjection(projectionList);
        criteria.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<HashMap<String, Object>> result = criteria.list();
        return result;
    }

    private List<HashMap<String, Object>> getListaDeHashMapsContendoOsContatosDoBanco(EntityManager entityManager) {
        //testar se esta refatoração deu certo e implementar nas outras classes
        List<String> colunasDaTabelaContatos = new ArrayList<String>();
        colunasDaTabelaContatos.add("idContato");
        colunasDaTabelaContatos.add("nome");
        colunasDaTabelaContatos.add("telefone");
        colunasDaTabelaContatos.add("email");
        colunasDaTabelaContatos.add("idFormularioNotFk");
        Contato contato = new Contato();
        List<HashMap<String, Object>> result = getListaDeHashMaps(entityManager,colunasDaTabelaContatos,contato);
        return result;
    }

    private List<HashMap<String, Object>> getListaDeHashMaps(EntityManager entityManager, List<String> colunasDaTabela, Object classe){
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria((Class) classe,"bean");
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
