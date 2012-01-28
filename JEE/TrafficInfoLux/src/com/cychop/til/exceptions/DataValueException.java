package com.cychop.til.exceptions;

/**
 * A simple exception implementation, used in the Traffic Info Lux project when
 * data seems inappropriated.
 * 
 * @author Cyrille Chopelet (cyrille.chopelet@mines-nancy.org)
 * @version 1.0
 * 
 */
public class DataValueException extends Exception {

	private static final long serialVersionUID = 5104619303392413001L;

	private String msg;

	/**
	 * Constructs a new exception without a message.
	 */
	public DataValueException() {
		this(null);
	}

	/**
	 * Constructs a new exception storing a message to help debugger find the
	 * cause of the exception.
	 * 
	 * @param msg
	 */
	public DataValueException(String msg) {
		super();
		this.msg = msg;
	}

	/**
	 * Returns the message that was stored at the creation of the
	 * <code>Exception</code>
	 * 
	 * @return the message that was stored at the creation of the
	 *         <code>Exception</code>
	 */
	public String getMsg() {
		return (this.msg == null) ? "No message stored." : this.msg;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.msg;
	}

}