package com.righettod.jee6jpa;

import com.righettod.jee6jpa.entity.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Second samples set : Read data using Criteria API
 *
 * @author Dominique Righetto
 */
public class Sample02 extends BaseSample {

    /**
     * Entry point
     * @param args Cmd line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emFactory = null;
        EntityManager em = null;
        Product prod = null;
        CriteriaBuilder criteriaBuilder = null;
        CriteriaQuery<Product> criteriaQuery = null;
        Root<Product> product = null;
        List<Product> products = null;

        try {
            //Create a EntityManager instance using EntityManagerFactory and a CriteriaBuilder
            emFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
            em = emFactory.createEntityManager();
            criteriaBuilder = em.getCriteriaBuilder();


            //Select Products using a parameter on name
            System.out.println("*** SELECT PRODUCTS USING A PARAMETER ON NAME");
            criteriaQuery = criteriaBuilder.createQuery(Product.class);
            product = criteriaQuery.from(Product.class);
            criteriaQuery.select(product).where(criteriaBuilder.equal(product.get("name"), "PS3"));
            try {
                prod = em.createQuery(criteriaQuery).getSingleResult();
                System.out.printf("[%s] : %s, %s\n", prod.getId(), prod.getName(), prod.getDescription());
            } catch (NoResultException nre) {
                //Exception throw by "getSingleResult()" if no result is returned by the query
                System.out.println("No product found !");
            } catch (NonUniqueResultException nure) {
                //Exception throw by "getSingleResult()" if several record is found by the query
                System.out.println("More than one record exists for this ID !");
            }


            //Select Products containing the letter "I" in their name
            System.out.println("*** SELECT PRODUCTS CONTAINING THE LETTER 'I' IN THEIR NAME");
            criteriaQuery = criteriaBuilder.createQuery(Product.class);
            product = criteriaQuery.from(Product.class);
            criteriaQuery.select(product).where(criteriaBuilder.like(product.<String>get("name"), "%I%"));
            products = em.createQuery(criteriaQuery).getResultList();
            for (Product p : products) {
                System.out.printf("[%s] : %s, %s\n", p.getId(), p.getName(), p.getDescription());
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
