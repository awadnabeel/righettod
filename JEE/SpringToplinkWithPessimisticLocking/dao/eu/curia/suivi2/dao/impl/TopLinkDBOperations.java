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
package eu.curia.suivi2.dao.impl;

import oracle.toplink.queryframework.ReadObjectQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.toplink.support.TopLinkDaoSupport;

import eu.curia.suivi2.dao.DBOperations;
import eu.curia.suivi2.model.Developer;

/**
 * TopLink implementation of Database operations
 * 
 * @author dominique.righetto@curia.europa.eu
 * 
 */
public class TopLinkDBOperations extends TopLinkDaoSupport implements
		DBOperations {

	/**
	 * @see eu.curia.suivi2.dao.DBOperations#readDeveloperById(int)
	 * 
	 * {@inheritDoc}
	 */
	@SuppressWarnings("boxing")
	public Developer readDeveloperById(int id) throws DataAccessException {

		Developer d = new Developer();
		ReadObjectQuery read = new ReadObjectQuery(Developer.class);
		d.setIdDeveloper(id);
		read.setExampleObject(d);

		Object o = getTopLinkTemplate().executeQuery(read);
		return (o != null) ? (Developer) o : null;
	}

	/**
	 * @see eu.curia.suivi2.dao.DBOperations#readAndLockDeveloperById(int)
	 * 
	 * {@inheritDoc}
	 */
	@SuppressWarnings("boxing")
	public Developer readAndLockDeveloperById(int id)
			throws DataAccessException {

		Developer d = new Developer();
		ReadObjectQuery read = new ReadObjectQuery(Developer.class);
		d.setIdDeveloper(id);
		read.setExampleObject(d);
		read.acquireLocksWithoutWaiting();

		Object o = getTopLinkTemplate().executeQuery(read);
		return (o != null) ? (Developer) o : null;
	}

	/**
	 * @see eu.curia.suivi2.dao.DBOperations#updateDeveloper(eu.curia.suivi2.model.Developer)
	 * 
	 * {@inheritDoc}
	 */
	public void updateDeveloper(Developer d) throws DataAccessException {
		getTopLinkTemplate().deepMerge(d);
	}

}
