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

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import eu.curia.suivi2.datamanager.Reader;
import eu.curia.suivi2.datamanager.ReaderLock;
import eu.curia.suivi2.datamanager.Updater;

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
			final ReaderLock readerLock = (ReaderLock) ctx
					.getBean("readerLockThread");
			final Updater updater = (Updater) ctx.getBean("updaterThread");

			// Get thread
			Thread thReader = new Thread() {
				@Override
				public void run() {
					reader.action();
				}
			};
			Thread thReaderLock = new Thread() {
				@Override
				public void run() {
					readerLock.action();
				}
			};
			Thread thUpdater = new Thread() {
				@Override
				public void run() {
					updater.action();
				}
			};

			// Run test
			ScheduledExecutorService scheduler = Executors
					.newScheduledThreadPool(4);
			scheduler.scheduleWithFixedDelay(thReader, 0, 1, TimeUnit.SECONDS);
			scheduler.scheduleWithFixedDelay(thReaderLock, 1, 2,
					TimeUnit.SECONDS);
			scheduler.scheduleWithFixedDelay(thUpdater, 2, 3, TimeUnit.SECONDS);

		} catch (ClassCastException cce) {
			cce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
