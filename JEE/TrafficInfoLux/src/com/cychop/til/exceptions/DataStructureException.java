package com.cychop.til.exceptions;

/**
 * A simple exception implementation, used in the Traffic Info Lux project when
 * the structure of the source file seems to have changed. Whenever such an
 * exception is raised, the application administrator should check if CITA has
 * changed the format of their file.
 * 
 * @author Cyrille Chopelet (cyrille.chopelet@mines-nancy.org)
 * @version 1.0
 * 
 */
public class DataStructureException extends Exception {

	private static final long serialVersionUID = -4682969058579845942L;

	private String msg;

	/**
	 * Constructs a new exception without a message.
	 */
	public DataStructureException() {
		this(null);
	}

	/**
	 * Constructs a new exception storing a message to help debugger find the
	 * cause of the exception.
	 * 
	 * @param msg
	 */
	public DataStructureException(String msg) {
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