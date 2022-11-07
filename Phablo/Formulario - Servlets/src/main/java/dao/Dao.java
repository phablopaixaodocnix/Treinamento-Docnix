package dao;

import model.Contato;
import model.Endereco;
import model.Formulario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Formulario> listarFormularios(){//Read
        EntityManager entityManager = jpaUtil.getEntityManeger();
        String jpql = "SELECT f from Formulario as f";
        List<Formulario> formularios = entityManager.createQuery(jpql, Formulario.class).getResultList();
        entityManager.close();
        return formularios;
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

}
