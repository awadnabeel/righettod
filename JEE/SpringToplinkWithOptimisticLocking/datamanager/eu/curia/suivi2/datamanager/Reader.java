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

import org.springframework.dao.DataAccessException;

/**
 * Class performing only read operation
 * 
 * @author dominique.righetto@curia.europa.eu
 * 
 */
public class Reader {

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
	 * @throws DataAccessException
	 */
	public void action() throws DataAccessException {
		String name = "Reader:" + Thread.currentThread().getId() + ":";
		System.out.println("==============================");
		System.out.printf("THREAD ID        : %s\n", name);
		// Extract and display developer information
		Developer d = this.dbDao.readDeveloperById(DEVELOPER_ID);
		System.out.printf("DEV ID           : %s\n", d.getIdDeveloper());
		System.out.printf("DEV LOCK VERS ID : %s\n", d.getOptimisticLockId());
		System.out.printf("DEV NAME         : %s\n", d.getNameDeveloper());
		System.out.printf("DEV LEVEL        : %s\n", d.getIdDeveloperLevel()
				.getLabelLevel());
		System.out.printf("LEV LOCK VERS ID : %s\n", d.getIdDeveloperLevel()
				.getOptimisticLockId());
		System.out.println("==============================");
	}

}
