package dao;

import model.Contato;
import model.Endereco;
import model.Formulario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class Dao {
    JPAUtil jpaUtil;

    public Dao(){
        jpaUtil = new JPAUtil();
    }

    public void cadastrarFormulario(Formulario f){//Create
        EntityManager entityManager = jpaUtil.getEntityManeger();
        entityManager.getTransaction().begin();
        entityManager.persist(f); //Assegura que f está no modo maneged
        //entityManager.flush(); //sincroniza os dados com o banco, ele salva mas não efetua o commit, ou seja, a transação ainda não terminou e pode ser desfeita fazendo um rollback caso ocorra uma exceção
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Formulario> listarFormularios(){//Read
        EntityManager entityManager = jpaUtil.getEntityManeger();
        String jpql = "SELECT f from Formulario as f";
        return entityManager.createQuery(jpql,Formulario.class).getResultList();
    }

    public List<Endereco> listarOEnderecoDeUmFormulario(Formulario f){//Read
        EntityManager entityManager = jpaUtil.getEntityManeger();
        String jpql = "SELECT e from Endereco as e WHERE idFormulario = :idFormulario";
        Query q = entityManager.createQuery(jpql);
        q.setParameter("idFormulario",f.getIdFormulario());
        return q.getResultList();
    }

    public List<Contato> listarContatosDeUmFormulario(Formulario f){//Read
        EntityManager entityManager = jpaUtil.getEntityManeger();
        String jpql = "SELECT c from Contato as c WHERE idFormulario = :idFormulario";
        Query q = entityManager.createQuery(jpql);
        q.setParameter("idFormulario",f.getIdFormulario());
        return q.getResultList();
    }

    public void atualizarFormulario(Formulario formulario/*o formulário f,que já está no banco,já foi atualizado da maneira necessária e será sincronizado com o banco*/){//update
        EntityManager entityManager = jpaUtil.getEntityManeger();
        entityManager.getTransaction().begin();
        Formulario formularioASerEditado = entityManager.find(Formulario.class,formulario.getIdFormulario());
        formularioASerEditado.setNome(formulario.getNome());
        formularioASerEditado.setEmail(formulario.getEmail());
        formularioASerEditado.setEscolaridade(formulario.getEscolaridade());
        formularioASerEditado.setEndereço(formulario.getEndereço());
        for(int i = 0 ; i < formularioASerEditado.getContatos().size();i++){
            formularioASerEditado.getContatos().get(i).setNome(formulario.getContatos().get(i).getNome());
            formularioASerEditado.getContatos().get(i).setEmail(formulario.getContatos().get(i).getEmail());
            formularioASerEditado.getContatos().get(i).setTelefone(formulario.getContatos().get(i).getTelefone());
        }
        entityManager.merge(formularioASerEditado);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deletarFormulario(Formulario formulario){//delete
        EntityManager entityManager = jpaUtil.getEntityManeger();
        entityManager.getTransaction().begin();
        Formulario formularioASerDeletado = entityManager.find(Formulario.class,formulario.getIdFormulario());
        entityManager.remove(formularioASerDeletado);
        entityManager.getTransaction().commit();
    }

}
