package com.sda.taskmanager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAFactory {
    private static EntityManagerFactory entityManagerFactory;
    private static final String USER_PERSISTENCE_NAME = "users_persistence";

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory(USER_PERSISTENCE_NAME);
    }

    private static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static EntityManager getUserEntityManager(){
        return entityManagerFactory.createEntityManager();
    }
}
