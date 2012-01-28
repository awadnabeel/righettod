package com.drighetto.fdr.transversal;

/**
 * Project custom exception
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class FDRException extends Exception {

	/**
	 * Serial VUID
	 */
	private static final long serialVersionUID = 1135395872850178621L;

	/**
	 * Constructor
	 * 
	 * @param cause
	 *        Source exception
	 */
	public FDRException(Throwable cause) {
		super(cause);
	}

}
