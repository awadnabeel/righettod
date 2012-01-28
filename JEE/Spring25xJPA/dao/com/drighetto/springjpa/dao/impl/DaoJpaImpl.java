package com.drighetto.springjpa.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Repository;

import com.drighetto.springjpa.dao.Dao;
import com.drighetto.springjpa.model.Developer;
import com.drighetto.springjpa.model.DeveloperEmployer;
import com.drighetto.springjpa.model.DeveloperLevel;

/**
 * JPA implementation of the DAO
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
@Repository
public class DaoJpaImpl extends JpaDaoSupport implements Dao {

	/**
	 * @see com.drighetto.springjpa.dao.Dao#createDeveloper(com.drighetto.springjpa.model.Developer)
	 * 
	 * {@inheritDoc}
	 */
	public void createDeveloper(Developer developer) throws DataAccessException {
		getJpaTemplate().persist(developer);
	}

	/**
	 * @see com.drighetto.springjpa.dao.Dao#deleteDeveloper(java.math.BigDecimal)
	 * 
	 * {@inheritDoc}
	 */
	public void deleteDeveloper(BigDecimal developerID)
			throws DataAccessException {
		// The entity you delete must be managed: that is, it must have been
		// previously read in the current persistence context
		Developer d = readDeveloper(developerID);
		getJpaTemplate().remove(d);
	}

	/**
	 * @see com.drighetto.springjpa.dao.Dao#readAllDeveloper()
	 * 
	 * {@inheritDoc} <br>
	 * <br>
	 * <b>This method use a NamedQuery defined in the bean
	 * "com.drighetto.springjpa.model.Developer" with the annotation
	 * "NamedQuery"</b>
	 */
	@SuppressWarnings("unchecked")
	public List<Developer> readAllDeveloper() throws DataAccessException {
		return getJpaTemplate().findByNamedQuery("findAllDevelopers");
	}

	/**
	 * @see com.drighetto.springjpa.dao.Dao#readDeveloper(java.math.BigDecimal)
	 * 
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Developer readDeveloper(BigDecimal developerId)
			throws DataAccessException {
		return getJpaTemplate().find(Developer.class, developerId);
	}

	/**
	 * @see com.drighetto.springjpa.dao.Dao#updateDeveloper(com.drighetto.springjpa.model.Developer)
	 * 
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unused")
	public void updateDeveloper(Developer developer) throws DataAccessException {
		// No update method is needed in a DAO because modifying an entity is as
		// simple as reading that entity within a transaction and changing the
		// properties of that entity and when the transaction is committed the
		// modification are sent to the underlying database...
	}

	/**
	 * @see com.drighetto.springjpa.dao.Dao#deleteDeveloperEmployer(java.math.BigDecimal)
	 * 
	 * {@inheritDoc}
	 */
	public void deleteDeveloperEmployer(BigDecimal developerEmployerID)
			throws DataAccessException {
		// The entity you delete must be managed: that is, it must have been
		// previously read in the current persistence context
		DeveloperEmployer de = getJpaTemplate().find(DeveloperEmployer.class,
				developerEmployerID);
		getJpaTemplate().remove(de);
	}

	/**
	 * @see com.drighetto.springjpa.dao.Dao#deleteDeveloperLevel(java.math.BigDecimal)
	 * 
	 * {@inheritDoc}
	 */
	public void deleteDeveloperLevel(BigDecimal developerLevelID)
			throws DataAccessException {
		// The entity you delete must be managed: that is, it must have been
		// previously read in the current persistence context
		DeveloperLevel dl = getJpaTemplate().find(DeveloperLevel.class,
				developerLevelID);
		getJpaTemplate().remove(dl);
	}

}
