package com.righettod.jee6jpa;

import com.righettod.jee6jpa.entity.Product;
import com.righettod.jee6jpa.entity.Shop;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * Fifth samples set: Show integration between JPA 2.0 and "Beans Validation".<br>
 * Annotations (package "javax.validation.constraints.*")" has been set on Entities fields
 * in order to describe values constraints and this samples set will
 * try to insert bad value in order to activate validators associated with theses annotations.<br>
 * By default validation occur during the @PrePersist, @PreUpdate and @PreRemove lifecycle phases.
 *
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 */
public class Sample05 extends BaseSample {

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


            /*Shop table*/
            //Load a shop entity instance
            Shop shop = em.find(Shop.class, 1);
            //Try to update values
            try {
                em.getTransaction().begin();
                //This value will activate the check on max size
                shop.setName("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                //In AUTO flush mode (default) the update on entity will be send at commit....
                em.getTransaction().commit();
            } catch (PersistenceException pe) {
                //No need to rollback because the exception have rollbacked and closed the transaction...
                if (pe.getCause() instanceof ConstraintViolationException) {
                    Set<ConstraintViolation<?>> constraintName = ((ConstraintViolationException)pe.getCause()).getConstraintViolations();
                    System.out.println("ConstraintViolationException throw ! => " + constraintName);
                }
            }

            /*Product table*/
            //Load a product entity instance
            Product product = em.find(Product.class, 1);
            //Try to update values
            try {
                em.getTransaction().begin();
                //This value will activate the check on digits
                product.setPrice(BigDecimal.valueOf(235.345));
                //In AUTO flush mode (default) the update on entity will be send at commit....
                em.getTransaction().commit();
            } catch (PersistenceException pe) {
                //No need to rollback because the exception have rollbacked and closed the transaction...
                if (pe.getCause() instanceof ConstraintViolationException) {
                    Set<ConstraintViolation<?>> constraintName = ((ConstraintViolationException)pe.getCause()).getConstraintViolations();
                    System.out.println("ConstraintViolationException throw ! => " + constraintName);
                }
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
