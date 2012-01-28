package com.drighetto.junit;

/**
 * Simple processing class
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 */
public class ProcessingBean {

	/** Message to display */
	private String message = null;

	/**
	 * 
	 * Default constructor
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         28 avr. 07<br>
	 * @param msg
	 *            Msg to display
	 */
	public ProcessingBean(String msg) {
		super();
		this.message = msg;
	}

	/**
	 * Simple processing method
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         28 avr. 07<br>
	 */
	public void displayMsg() {
		System.out.printf("MESSAGE = %s\n", this.message);
	}

	/**
	 * Getter for the attribute message
	 * @return The value of message
	 */
	public String getMessage() {
		return this.message;
	}
	
	/**
	 * Return the current instance
	 * @return the current instance
	 */
	public Object getInstance(){
		return this;
	}
	
	

}
