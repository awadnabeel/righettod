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
package eu.curia.suivi2;

import eu.curia.suivi2.datamanager.Reader;
import eu.curia.suivi2.datamanager.Updater;
import eu.curia.suivi2.datamanager.UpdaterNoWait;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.toplink.TopLinkOptimisticLockingFailureException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Main
 * 
 */
public class Main {

	/**
	 * Main
	 * 
	 * @param args
	 *            Cmd
	 */
	public static void main(String[] args) {
		try {

			// Create Spring Context
			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
					"applicationContext.xml");

			// Get bean
			final Reader reader = (Reader) ctx.getBean("readerThread");
			final Updater updater = (Updater) ctx.getBean("updaterThread");
			final UpdaterNoWait updaterNoWait = (UpdaterNoWait) ctx
					.getBean("updaterNoWaitThread");

			// Get thread
			Thread thReader = new Thread() {
				@SuppressWarnings("boxing")
				@Override
				public void run() {
					try {
						reader.action();
					} catch (DataAccessException dae) {
						// Check if there is a lock exception...
						if (dae instanceof TopLinkOptimisticLockingFailureException) {
							System.out
									.printf(
											"THREAD ID        : %s --> Version différente !!!\n",
											Thread.currentThread().getId());
						} else {
							System.out
									.printf(
											"THREAD ID        : %s --> Exception : %s !!!\n",
											Thread.currentThread().getId(), dae
													.getMessage());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};

			Thread thUpdater = new Thread() {
				@SuppressWarnings("boxing")
				@Override
				public void run() {
					try {
						updater.action();
						System.out.printf(
								"THREAD ID        : %s --> Data updated !!!\n",
								Thread.currentThread().getId());
					} catch (DataAccessException dae) {
						// Check if there is a lock exception...
						if (dae instanceof TopLinkOptimisticLockingFailureException) {
							System.out
									.printf(
											"THREAD ID        : %s --> Version différente !!!\n",
											Thread.currentThread().getId());
						} else {
							System.out
									.printf(
											"THREAD ID        : %s --> Exception : %s !!!\n",
											Thread.currentThread().getId(), dae
													.getMessage());
						}
					} catch (InterruptedException ie) {
						ie.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			Thread thUpdaterNoWait = new Thread() {
				@SuppressWarnings("boxing")
				@Override
				public void run() {

					try {
						updaterNoWait.action();
						System.out.printf(
								"THREAD ID        : %s --> Data updated !!!\n",
								Thread.currentThread().getId());
					} catch (DataAccessException dae) {
						// Check if there is a lock exception...
						if (dae instanceof TopLinkOptimisticLockingFailureException) {
							System.out
									.printf(
											"THREAD ID        : %s --> Version différente !!!\n",
											Thread.currentThread().getId());
						} else {
							System.out
									.printf(
											"THREAD ID        : %s --> Exception : %s !!!\n",
											Thread.currentThread().getId(), dae
													.getMessage());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};

			// Run test
			ScheduledExecutorService scheduler = Executors
					.newScheduledThreadPool(3);
			scheduler.scheduleWithFixedDelay(thReader, 0, 1, TimeUnit.SECONDS);
			scheduler.scheduleWithFixedDelay(thUpdater, 2, 5, TimeUnit.SECONDS);
			scheduler.scheduleWithFixedDelay(thUpdaterNoWait, 4, 3,
					TimeUnit.SECONDS);

		} catch (ClassCastException cce) {
			cce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
