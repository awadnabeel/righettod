package com.drighetto.lmb.transversal.error;

/**
 * Project custom exception
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class LMBException extends Exception {

	/**
	 * Serial VUID
	 */
	private static final long serialVersionUID = 2613591492546038692L;

	/**
	 * Constructor
	 * 
	 * @param cause
	 *        Exception cause
	 * 
	 */
	public LMBException(Throwable cause) {
		super(cause);
	}

}
