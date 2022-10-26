package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("Formulario-JPA-Hibernate");

    public EntityManager getEntityManeger(){
        EntityManager entityManager = FACTORY.createEntityManager();
        return entityManager;
    }
}