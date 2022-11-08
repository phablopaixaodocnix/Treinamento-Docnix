package dao;

import model.Contato;
import model.Endereco;
import model.Formulario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dao {
    private JPAUtil jpaUtil;

    public Dao(){
        this.jpaUtil = new JPAUtil();
    }

    public void cadastrarFormulario(Formulario f){//Create
        EntityManager entityManager = jpaUtil.getEntityManeger();
        entityManager.getTransaction().begin();
        entityManager.persist(f); //Assegura que f está no modo maneged
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void listarFormularios(){//Read
        EntityManager entityManager = jpaUtil.getEntityManeger();
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Formulario.class,"bean");

        criteria.createAlias("bean.endereco","endereco");
        criteria.createAlias("bean.contatos","contatos");

        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("bean.idFormulario").as("idFormulario"));
        projectionList.add(Projections.property("bean.nome").as("nome"));
        projectionList.add(Projections.property("bean.email").as("email"));
        projectionList.add(Projections.property("bean.cpf").as("cpf"));
        projectionList.add(Projections.property("bean.escolaridade").as("escolaridade"));
        projectionList.add(Projections.property("endereco.idEndereco").as("idEndereco"));
        projectionList.add(Projections.property("endereco.cidade").as("cidade"));
        projectionList.add(Projections.property("endereco.bairro").as("bairro"));
        projectionList.add(Projections.property("endereco.rua").as("rua"));
        projectionList.add(Projections.property("endereco.quadra").as("quadra"));
        projectionList.add(Projections.property("endereco.casa").as("casa"));
        projectionList.add(Projections.property("endereco.cep").as("cep"));
        projectionList.add(Projections.property("endereco.lote").as("lote"));
        projectionList.add(Projections.property("endereco.numero").as("numero"));
        projectionList.add(Projections.property("endereco.uf").as("uf"));
        projectionList.add(Projections.property("contatos.idContato").as("idContato"));
        projectionList.add(Projections.property("contatos.nome").as("nomeContato"));
        projectionList.add(Projections.property("contatos.telefone").as("telefoneContato"));
        projectionList.add(Projections.property("contatos.email").as("emailContato"));

        criteria.setProjection(projectionList);
        criteria.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<HashMap<String, Object>> result = criteria.list();

        List<Formulario> formularios = new ArrayList<Formulario>();

        for(int i = 0 ; i < result.size(); i++) {
            if(result.get(i).get("idFormulario") != result.get(i+1).get("idFormulario")) {
                //extrair este método para uma função própria e consertar a lógica da criação da lista de formulários a partir do hashset
                int idFormulario = (Integer) result.get(i).get("idFormulario");
                String nome = (String) result.get(i).get("nome");
                String email = (String) result.get(i).get("email");
                String cpf = (String) result.get(i).get("cpf");
                String escolaridade = (String) result.get(i).get("escolaridade");
                Formulario formularioApenasComOsDadosPessoais = new Formulario(idFormulario,nome,email,cpf,escolaridade);

                Endereco endereco = getEnderecoAPartirDaListaRetornadaPeloCriteria(result, i);

                Contato contato = getContatoAPartirDaListaRetornadaPeloCriteria(result, i);
                List<Contato> contatos = new ArrayList<Contato>();
                contatos.add(contato);

                Formulario formulario = new Formulario(idFormulario,nome,email,cpf,endereco,escolaridade,contatos);
                formularios.add(formulario);
            }
            else {
                Contato contato = getContatoAPartirDaListaRetornadaPeloCriteria(result, i);
                formularios.get(formularios.size()-1).getContatos().add(contato);
            }
        }


        entityManager.close();
    }

    public List<Endereco> listarEnderecos() {
        EntityManager entityManager = jpaUtil.getEntityManeger();
        String jpql = "SELECT e FROM Endereco AS e";
        List<Endereco> enderecos = entityManager.createQuery(jpql,Endereco.class).getResultList();
        entityManager.close();
        return enderecos;
    }

    public List<Contato> listarContatos() {
        EntityManager entityManager = jpaUtil.getEntityManeger();
        String jpql = "SELECT c FROM Contato AS c";
        List<Contato> contatos = entityManager.createQuery(jpql,Contato.class).getResultList();
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


    private static Endereco getEnderecoAPartirDaListaRetornadaPeloCriteria(List<HashMap<String, Object>> result, int i) {
        int idEndereco = (Integer) result.get(i).get("idEndereco");
        String cidade = (String) result.get(i).get("cidade");
        String bairro = (String) result.get(i).get("bairro");
        String rua = (String) result.get(i).get("rua");
        int quadra = (Integer) result.get(i).get("quadra");
        int casa = (Integer) result.get(i).get("casa");
        String cep = (String) result.get(i).get("cep");
        int lote = (Integer) result.get(i).get("lote");
        int numero = (Integer) result.get(i).get("numero");
        String uf = (String) result.get(i).get("uf");
        Endereco endereco = new Endereco(idEndereco, cidade, bairro, rua, quadra, casa, cep, lote, numero, uf);
        return endereco;
    }

    private static Contato getContatoAPartirDaListaRetornadaPeloCriteria(List<HashMap<String, Object>> result, int i) {
        int idContato = (Integer) result.get(i).get("idContato");
        String nomeContato = (String) result.get(i).get("nomeContato");
        String telefoneContato = (String) result.get(i).get("telefoneContato");
        String emailContato = (String) result.get(i).get("emailContato");
        Contato contato = new Contato(idContato, nomeContato, telefoneContato, emailContato);
        return contato;
    }

}
