package com.sda.taskmanager;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserDao {
    private static Logger log = Logger.getLogger(UserDao.class.getName());

    public boolean create(User user) {
        EntityManager manager = JPAFactory.getUserEntityManager();
        try {
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();

            manager.persist(user);

            transaction.commit();
        } catch (PersistenceException pe) {
            log.warning(pe.getMessage());
//            log.log(Level.SEVERE,
//                    pe.getMessage(),
//                    pe);
            return false;
        }

        return true;
    }

    public Optional<User> zaloguj(String nazwaUzytkownika, String haslo) {
        EntityManager manager = JPAFactory.getUserEntityManager();
        try {
            TypedQuery<User> userTypedQuery =
                    manager.createQuery("Select u from User u where u.nazwaUzytkownika = :un and u.haslo = :hs", User.class);
//                    manager.createQuery("from User where nazwaUzytkownika = :un and haslo = :hs", User.class);
            userTypedQuery.setParameter("un", nazwaUzytkownika);
            userTypedQuery.setParameter("hs", haslo);

            return Optional.ofNullable(userTypedQuery.getSingleResult());
        } catch (PersistenceException pe) {
            log.warning(pe.getMessage());
        }
        return Optional.empty();
    }
}
