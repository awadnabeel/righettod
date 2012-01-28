package com.drighetto.springjpa.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.drighetto.springjpa.model.Developer;

/**
 * Simple DAO defining basic CRUD operations
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
public interface Dao {

	/**
	 * Create a developer
	 * 
	 * @param developer
	 *            The developer informations
	 * @throws DataAccessException
	 */
	void createDeveloper(Developer developer) throws DataAccessException;

	/**
	 * Extract all developer
	 * 
	 * @return a list fo developer
	 * @throws DataAccessException
	 */
	List<Developer> readAllDeveloper() throws DataAccessException;

	/**
	 * Extract a developer
	 * 
	 * @param developerId
	 *            Developer ID
	 * @return a developer
	 * @throws DataAccessException
	 */
	Developer readDeveloper(BigDecimal developerId) throws DataAccessException;

	/**
	 * Update a developer
	 * 
	 * @param developer
	 *            Developer information
	 * @throws DataAccessException
	 */
	void updateDeveloper(Developer developer) throws DataAccessException;

	/**
	 * Developer a developer
	 * 
	 * @param developerID
	 *            Developer ID
	 * @throws DataAccessException
	 */
	void deleteDeveloper(BigDecimal developerID) throws DataAccessException;

	/**
	 * Developer a developer level
	 * 
	 * @param developerLevelID
	 *            Developer level ID
	 * @throws DataAccessException
	 */
	void deleteDeveloperLevel(BigDecimal developerLevelID)
			throws DataAccessException;

	/**
	 * Developer a developer employer
	 * 
	 * @param developerEmployerID
	 *            Developer employer ID
	 * @throws DataAccessException
	 */
	void deleteDeveloperEmployer(BigDecimal developerEmployerID)
			throws DataAccessException;

}
