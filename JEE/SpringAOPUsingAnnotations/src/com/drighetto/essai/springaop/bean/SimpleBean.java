package com.drighetto.essai.springaop.bean;

/**
 * Simple bean that will be advised by aspect
 * 
 * @author Dominique RIGHETTO<br>
 *         23 mars 07<br>
 */
public class SimpleBean {

	/**
	 * 
	 * Default constructor
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         23 mars 07<br>
	 */
	public SimpleBean() {
		super();
	}

	/**
	 * Simple "Hello World !" method
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         23 mars 07<br>
	 * @param message
	 *            Hello message
	 */
	public void sayHello(String message) {
		System.out.printf("\nI SAY [%s] FROM [%s]\n\n", message, this
				.getClass().getName());
	}

	/**
	 * Another simple "Hello World !" method
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         23 mars 07<br>
	 * @return String
	 */
	public String sayHello() {
		return "\nI SAY [Hello World !] FROM [" + this.getClass().getName() + "]\n\n";
	}	
}
