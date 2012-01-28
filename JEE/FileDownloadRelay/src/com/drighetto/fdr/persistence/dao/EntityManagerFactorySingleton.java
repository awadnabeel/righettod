package com.drighetto.fdr.persistence.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Singleton to manage the JPA EntityManagerFactory
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public abstract class EntityManagerFactorySingleton {

	/** EntityManagerFactory single instance */
	private static EntityManagerFactory EMF = null;

	/**
	 * Instance accessor
	 * 
	 * @return a EntityManagerFactory instance
	 */
	public static EntityManagerFactory getInstance() {
		if (EMF == null) {
			EMF = Persistence.createEntityManagerFactory("transactions-optional");
		}
		return EMF;
	}

}
