package com.righettod.jee6jpa;

import com.righettod.jee6jpa.entity.Product;
import com.righettod.jee6jpa.entity.Shop;
import java.math.BigDecimal;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * Third samples set : Write data using explicit transaction markup (programmatic transaction declaration)
 *
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 */
public class Sample03 extends BaseSample {

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

            //The lifetime of the EntityManager is restricted to the scope of the main method,
            //thus the attached (managed) entities managed by this EM are managed for the same lifetime...

            //Add a new shop
            Shop shop = em.find(Shop.class, 4);
            System.out.println("*** INSERT A NEW SHOP");
            //--Check that the shop do not exist
            if (shop == null) {
                //--Create a new object
                shop = new Shop();
                shop.setId(4);
                shop.setName("LIDLE");
                //--Begin a new transaction
                em.getTransaction().begin();
                try {
                    //--Try to persist object
                    em.persist(shop);
                    //--Commit current active transaction
                    em.getTransaction().commit();
                    System.out.println("Shop added !");
                } catch (Exception e) {
                    System.out.println("Error during add");
                    //--Rollback current active transaction
                    em.getTransaction().rollback();
                }
            } else {
                System.out.println("Shop already exists !");
            }

            //Update the added shop, add a new product to it
            System.out.println("*** UPDATE THE ADDED SHOP, ADD A NEW PRODUCT TO IT");
            //--Begin a transaction
            em.getTransaction().begin();
            //--Create new product (Product ID is generated)
            Product p = new Product();
            p.setName("GameGear number " + System.currentTimeMillis());
            p.setDescription("Console de jeu portable");
            p.setPrice(BigDecimal.valueOf(20.45));
            p.setShop(shop);
            try {
                //--Try to persist object
                em.persist(p);
                //--Commit current active transaction
                em.getTransaction().commit();
                System.out.println("Product added !");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error during add");
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
