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
package eu.curia.suivi2.datamanager;

import eu.curia.suivi2.dao.DBOperations;
import eu.curia.suivi2.model.Developer;
import oracle.toplink.exceptions.OptimisticLockException;

import org.springframework.dao.DataAccessException;

/**
 * Class performing update operation
 * 
 * @author dominique.righetto@curia.europa.eu
 * 
 */
public class UpdaterNoWait {

	/** Reference developer ID */
	private static final int DEVELOPER_ID = 1;

	/** DAO */
	private DBOperations dbDao;

	/**
	 * Setter for field dbDao
	 * 
	 * @param dbDao
	 *            the dbDao to set
	 */
	public void setDbDao(DBOperations dbDao) {
		this.dbDao = dbDao;
	}

	/**
	 * Action method
	 * 
	 * @throws OptimisticLockException
	 * @throws DataAccessException
	 */
	@SuppressWarnings("boxing")
	public void action() throws DataAccessException {
		String name = "UpdaterNoWait:" + Thread.currentThread().getId() + ":";

		// Extract developer information
		Developer d = this.dbDao.readDeveloperById(DEVELOPER_ID);
		// Modify object info
		d.setNameDeveloper(name);
		d.getIdDeveloperLevel().setLabelLevel("MIDDLE_" + name);
		// Update info
		this.dbDao.updateDeveloper(d);
		System.out.println("==============================");
		System.out.printf("THREAD ID        : %s\n", name);
		System.out.printf("DEV LOCK VERS ID : %s\n", d.getOptimisticLockId());
		System.out.printf("DEV ID           : %s\n", d.getIdDeveloper());
		System.out.printf("DEV NAME         : %s\n", d.getNameDeveloper());
		System.out.printf("DEV LEVEL        : %s\n", d.getIdDeveloperLevel()
				.getLabelLevel());
		System.out.printf("LEV LOCK VERS ID : %s\n", d.getIdDeveloperLevel()
				.getOptimisticLockId());
		System.out.println("==============================");

	}
}
