package com.drighetto.spring25x.jmx;

import java.text.DateFormat;
import java.util.Date;

/**
 * Simple POJO exposed as JMX MBean by Spring
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
public class SimpleMBean {

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
	public String getCState() {
		return this.cState;
	}

	/**
	 * Setter for the attribute cState
	 * 
	 * @param state
	 *            The new value
	 */
	public void setCState(String state) {
		this.cState = state;
	}

	/**
	 * Getter for the attribute cDate
	 * 
	 * @return The value of cDate
	 */
	public String getCDate() {
		return this.cDate;
	}

	/**
	 * Get the bean content
	 * 
	 * @return the content
	 */
	public String getStatus() {
		StringBuilder status = new StringBuilder("[CDATE : ")
				.append(this.cDate).append(" - CSTATE : ").append(this.cState)
				.append("]");
		return status.toString().trim();
	}

}
