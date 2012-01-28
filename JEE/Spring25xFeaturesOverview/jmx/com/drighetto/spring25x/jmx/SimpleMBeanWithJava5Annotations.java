package com.drighetto.spring25x.jmx;

import java.text.DateFormat;
import java.util.Date;

import javax.management.Notification;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;

/**
 * Simple POJO exposed as JMX MBean by Spring BUT using the Java5 JMX
 * annotations through Spring support and notification publishing features...
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
@ManagedResource(objectName = "bean:name=simpleMBeanWithJava5Annotations", description = "SimpleMBeanWithJava5Annotations")
public class SimpleMBeanWithJava5Annotations implements
		NotificationPublisherAware {

	/** JMX notification publisher */
	private NotificationPublisher publisher;

	/** Private properties exposed for READ ONLY mode */
	private final String cDate = DateFormat.getDateInstance()
			.format(new Date());

	/** Private properties exposed for READ/WRITE mode */
	private String cState = "DEFAULT";

	/**
	 * Getter for the attribute cState
	 * 
	 * @return The value of cState
	 */
	@ManagedAttribute(description = "The State Attribute")
	public String getCState() {
		this.publisher
				.sendNotification(new Notification("GETTER", this.toString(),
						0, System.currentTimeMillis(), "Call getCState()"));
		return this.cState;
	}

	/**
	 * Setter for the attribute cState
	 * 
	 * @param state
	 *            The new value
	 */
	@ManagedAttribute(description = "The State Attribute")
	public void setCState(String state) {
		this.publisher
				.sendNotification(new Notification("SETTER", this.toString(),
						0, System.currentTimeMillis(), "Call setCState()"));
		this.cState = state;
	}

	/**
	 * Getter for the attribute cDate
	 * 
	 * @return The value of cDate
	 */
	@ManagedAttribute(description = "The CDate Attribute")
	public String getCDate() {
		this.publisher.sendNotification(new Notification("GETTER", this
				.toString(), 0, System.currentTimeMillis(), "Call getCDate()"));
		return this.cDate;
	}

	/**
	 * Get the bean content
	 * 
	 * @return the content
	 */
	@ManagedOperation(description = "Get the MBean status")
	public String obtainStatus() {
		StringBuilder status = new StringBuilder("[CDATE : ")
				.append(this.cDate).append(" - CSTATE : ").append(this.cState)
				.append("]");
		this.publisher.sendNotification(new Notification("STATUS", this
				.toString(), 0, System.currentTimeMillis(),
				"Call obtainStatus()"));
		return status.toString().trim();
	}

	/**
	 * @see org.springframework.jmx.export.notification.NotificationPublisherAware#setNotificationPublisher(org.springframework.jmx.export.notification.NotificationPublisher)
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void setNotificationPublisher(
			NotificationPublisher notificationPublisher) {
		this.publisher = notificationPublisher;
	}

}
