package com.drighetto.fdr.persistence.dao;

import javax.persistence.EntityManager;

import com.drighetto.fdr.persistence.model.FileInformations;
import com.drighetto.fdr.transversal.FDRException;

/**
 * JPA Impl. of the file DAO
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class FileDaoJPAImpl implements FileDao {

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.drighetto.fdr.persistence.dao.FileDao#save(com.drighetto.fdr.persistence.model.FileInformations)
	 */
	public void save(FileInformations informations) throws FDRException {
		EntityManager em = null;
		try {
			// Get a EntityManager instance
			em = EntityManagerFactorySingleton.getInstance().createEntityManager();
			// Persist the file informations
			em.persist(informations);
		} catch (Exception e) {
			throw new FDRException(e);
		} finally {
			// Release the EntityManager
			if (em != null) {
				em.close();
			}
		}
	}

}
