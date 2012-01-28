package com.righettod.jee6jpa;

import com.righettod.jee6jpa.entity.Shop;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Fourth samples set : Show orphan record removal using explicit transaction markup<br>
 * Do not forget the set to "true" the attribute "orphanRemoval" on father relationship annotation !
 *
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 */
public class Sample04 extends BaseSample {

    /**
     * Entry point
     * @param args Cmd line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emFactory = null;
        EntityManager em = null;

        try {
            //Create a EntityManager instance using EntityManagerFactory
            emFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
            em = emFactory.createEntityManager();

            //Call Sample03 in to create record hierarchy for father object
            for (int i = 0; i < 4; i++) {
                Sample03.main(args);
            }

            //Load father object
            Shop shop = em.find(Shop.class, 4);
            //Remove it in a explicit transaction
            //--Begin a new transaction
            em.getTransaction().begin();
            try {
                //--Try to remove object
                em.remove(shop);
                //--Commit current active transaction
                em.getTransaction().commit();
                System.out.println("Shop removed !");
            } catch (Exception e) {
                System.out.println("Error during remove");
                //--Rollback current active transaction
                em.getTransaction().rollback();
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
