package com.drighetto.essai.axis2.services;

import java.io.Serializable;

/**
 * Simple pojo
 * 
 * @author Dominique RIGHETTO<br>
 *         4 mai 07<br>
 */
@SuppressWarnings("boxing")
public class Person implements Serializable {

	private static final long serialVersionUID = 1883426517502203203L;

	private String name = "";

	private int age = 0;
	

	/**
	 * Default Constructor Constructor
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         4 mai 07<br>
	 */
	public Person() {		
		super();
	}

	/**
	 * Constructor
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         4 mai 07<br>
	 * @param name
	 * @param age
	 */
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	/**
	 * Getter for age<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         4 mai 07<br>
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Setter for age<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         4 mai 07<br>
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Getter for name<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         4 mai 07<br>
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         4 mai 07<br>
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
