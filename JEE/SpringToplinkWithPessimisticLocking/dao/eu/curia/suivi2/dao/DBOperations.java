/**
 * Copyright (c) 2007 by Curia Luxembourg - Court of Justice of the European Communities
 * All Rights Reserved.
 *
 * This product and related documentation are protected by copyright restricting its use, 
 * copying, distribution, and decompilation. No part of this product or related documentation 
 * may be reproduced in any form by any means without prior written authorization of Curia
 * Luxembourg or its partners, if any. Unless otherwise arranged, third parties may not have access to 
 * this product or related documentation.
 */
package eu.curia.suivi2.dao;

import org.springframework.dao.DataAccessException;

import eu.curia.suivi2.model.Developer;

/**
 * Database operations
 * 
 * @author dominique.righetto@curia.europa.eu
 * 
 */
public interface DBOperations {

	/**
	 * Method to read and lock a extract developer informations by ID
	 * 
	 * @param id
	 *            Developer ID
	 * @return A objet representing a developer
	 * @throws DataAccessException
	 */
	Developer readAndLockDeveloperById(int id) throws DataAccessException;

	/**
	 * Method to read a extract developer informations by ID
	 * 
	 * @param id
	 *            Developer ID
	 * @return A objet representing a developer
	 * @throws DataAccessException
	 */
	Developer readDeveloperById(int id) throws DataAccessException;

	/**
	 * Method to update a developer
	 * 
	 * @param d
	 *            Developer to update
	 * @throws DataAccessException
	 */
	void updateDeveloper(Developer d) throws DataAccessException;

}
