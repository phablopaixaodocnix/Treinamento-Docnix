package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private EntityManagerFactory FACTORY;

    public JPAUtil(){
        this.FACTORY = Persistence.createEntityManagerFactory("FormularioServlet");
        int i = 0;
    }

    public EntityManager getEntityManeger(){
        return this.FACTORY.createEntityManager();
    }
}