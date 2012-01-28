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

import org.springframework.orm.toplink.TopLinkJdbcException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import eu.curia.suivi2.dao.DBOperations;
import eu.curia.suivi2.model.Developer;

/**
 * Class performing only read operation
 * 
 * @author dominique.righetto@curia.europa.eu
 * 
 */
public class ReaderLock {

	/** Reference developer ID */
	private static final int DEVELOPER_ID = 1;

	/** Date formatter */
	private static final DateFormat DATE_FORMATTER = new SimpleDateFormat(
			"yyyyMMdd-HHmmssS");

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
	 */
	public void action() {
		String name = "ReaderLock_" + DATE_FORMATTER.format(new Date());
		try {
			System.out.println("==============================");
			System.out.printf("THREAD ID : %s\n", name);
			// Extract and display developer information
			Developer d = this.dbDao.readAndLockDeveloperById(DEVELOPER_ID);
			System.out.printf("DEV ID    : %s\n", d.getIdDeveloper());
			System.out.printf("DEV NAME  : %s\n", d.getNameDeveloper());
			System.out.printf("DEV LEVEL : %s\n", d.getIdDeveloperLevel()
					.getLabelLevel());
		} catch (TopLinkJdbcException tplje) {
			System.out.printf("THREAD ID : %s --> Enregistement locké !!!\n",
					name);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("==============================");
		}

	}

}
