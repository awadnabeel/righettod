package com.drighetto.essai.springcontextaware;

/**
 * Simple pojo - Spring Managed
 * 
 * @author Dominique RIGHETTO<br>
 *         7 janv. 08<br>
 */
public class SimplePojo {

	/** Simple message */
	private String message = null;

	/**
	 * Getter for message<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         7 janv. 08<br>
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Setter for message<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         7 janv. 08<br>
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
