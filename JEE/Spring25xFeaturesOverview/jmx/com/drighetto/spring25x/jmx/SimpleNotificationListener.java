package com.drighetto.spring25x.jmx;

import javax.management.Notification;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;

/**
 * Simple JMX notification listener
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
public class SimpleNotificationListener implements NotificationListener,
		NotificationFilter {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -6966674254969106585L;

	/**
	 * @see javax.management.NotificationListener#handleNotification(javax.management.Notification,
	 *      java.lang.Object)
	 * 
	 * {@inheritDoc}
	 */
	@SuppressWarnings("boxing")
	@Override
	public void handleNotification(Notification notification, Object handback) {
		System.out
				.println("\n***SimpleNotificationListener:handleNotification*****************");
		System.out.printf("* TIMESTAMP       : %s\n", notification
				.getTimeStamp());
		System.out.printf("* SEQUENCE NUMBER : %s\n", notification
				.getSequenceNumber());
		System.out.printf("* SOURCE          : %s\n", notification.getSource());
		System.out.printf("* TYPE            : %s\n", notification.getType());
		System.out
				.printf("* MESSAGE         : %s\n", notification.getMessage());
		System.out.printf("* USER DATA       : %s\n", notification
				.getUserData());
		System.out.printf("* HANDBACK OBJECT : %s\n", handback);
		System.out
				.println("*****************************************************************");

	}

	/**
	 * @see javax.management.NotificationFilter#isNotificationEnabled(javax.management.Notification)
	 * 
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unused")
	public boolean isNotificationEnabled(Notification notification) {
		return true;
	}

}
