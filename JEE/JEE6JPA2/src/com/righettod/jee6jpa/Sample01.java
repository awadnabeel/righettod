package com.righettod.jee6jpa;

import com.righettod.jee6jpa.entity.Shop;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.PessimisticLockException;
import javax.persistence.TypedQuery;

/**
 * First samples set : Read data using JPA QL Expression
 * 
 * @author Dominique Righetto
 */
public class Sample01 extends BaseSample {

    /**
     * Entry point
     * @param args Cmd line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emFactory = null;
        EntityManager em = null;
        TypedQuery<Shop> queryShop = null;
        List<Shop> shops = null;
        Shop shop = null;

        try {
            //Create a EntityManager instance using EntityManagerFactory
            emFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
            em = emFactory.createEntityManager();


            //Select all shops
            System.out.println("*** SELECT ALL SHOPS");
            queryShop = em.createQuery("SELECT s FROM Shop s", Shop.class);
            shops = queryShop.getResultList();
            for (Shop s : shops) {
                System.out.printf("[%s] : %s\n", s.getId(), s.getName());
            }


            //Select a shop using is id
            System.out.println("*** SELECT A SHOP USING IS ID");
            System.out.println("** NAMED PARAMETER VERSION");
            queryShop = em.createQuery("SELECT s FROM Shop s WHERE s.id = :id", Shop.class);
            try {
                shop = queryShop.setParameter("id", 2).getSingleResult();
                System.out.printf("[%s] : %s\n", shop.getId(), shop.getName());
            } catch (NoResultException nre) {
                //Exception throw by "getSingleResult()" if no result is returned by the query
                System.out.println("No shop found !");
            } catch (NonUniqueResultException nure) {
                //Exception throw by "getSingleResult()" if several record is found by the query
                System.out.println("More than one record exists for this ID !");
            }
            System.out.println("** ORDINAL PARAMETER VERSION");
            queryShop = em.createQuery("SELECT s FROM Shop s WHERE s.id = ?1", Shop.class);
            try {
                shop = queryShop.setParameter(1, 2).getSingleResult();
                System.out.printf("[%s] : %s\n", shop.getId(), shop.getName());
            } catch (NoResultException nre) {
                //Exception throw by "getSingleResult()" if no result is returned by the query
                System.out.println("No shop found !");
            } catch (NonUniqueResultException nure) {
                //Exception throw by "getSingleResult()" if several record is found by the query
                System.out.println("More than one record exists for this ID !");
            }


            //Select shops with a range restriction
            System.out.println("*** SELECT SHOPS WITH A RANGE RESTRICTION");
            queryShop = em.createQuery("SELECT s FROM Shop s", Shop.class);
            shops = queryShop.setFirstResult(1).setMaxResults(2).getResultList();
            for (Shop s : shops) {
                System.out.printf("[%s] : %s\n", s.getId(), s.getName());
            }


            //Select all shops using a predefined Named Query
            System.out.println("*** SELECT SHOPS USING A PREDEFINED NAMED QUERY");
            queryShop = em.createNamedQuery("Shop.findAll", Shop.class);
            shops = queryShop.getResultList();
            for (Shop s : shops) {
                System.out.printf("[%s] : %s\n", s.getId(), s.getName());
            }


            //Select a shop using a pessimistic lock
            System.out.println("*** SELECT A SHOPS USING PESSIMISTIC LOCK");
            try {
                shop = em.find(Shop.class, 2, LockModeType.PESSIMISTIC_READ);
                System.out.printf("[%s] : %s\n", shop.getId(), shop.getName());
            } catch (PessimisticLockException ple) {
                System.out.println("Unable to obtains lock on record !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Release EntityManager
            if (em != null) {
                try {
                    em.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //Release EntityManagerFactory
            if (emFactory != null) {
                try {
                    emFactory.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
