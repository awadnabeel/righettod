package com.cychop.til.exceptions;

/**
 * A simple exception implementation, used in the Traffic Info Lux project when
 * the CITA source file cannot be read
 * 
 * @author Cyrille Chopelet (cyrille.chopelet@mines-nancy.org)
 * @version 1.0
 * 
 */
public class SourceUnavailableException extends Exception {

	private static final long serialVersionUID = -9213103877235923860L;

	private String msg;

	/**
	 * Constructs a new exception without a message.
	 */
	public SourceUnavailableException() {
		this(null);
	}

	/**
	 * Constructs a new exception storing a message to help debugger find the
	 * cause of the exception.
	 * 
	 * @param msg
	 */
	public SourceUnavailableException(String msg) {
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