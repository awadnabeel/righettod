package com.drighetto.xstream;

/**
 * Simple POJO
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
public class SimplePojo {

	/** Msg */
	private String message = null;

	/** Sender */
	private String sender = null;

	/**
	 * Getter for the attribute message
	 * 
	 * @return The value of message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Setter for the attribute message
	 * 
	 * @param message
	 *            The new value
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Getter for the attribute sender
	 * 
	 * @return The value of sender
	 */
	public String getSender() {
		return this.sender;
	}

	/**
	 * Setter for the attribute sender
	 * 
	 * @param sender
	 *            The new value
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean state = false;

		if (obj instanceof SimplePojo) {
			SimplePojo sp = (SimplePojo) obj;
			if (this.message.equals(sp.getMessage())
					&& this.sender.equals(sp.getSender())) {
				state = true;
			}
		}

		return state;
	}

}
