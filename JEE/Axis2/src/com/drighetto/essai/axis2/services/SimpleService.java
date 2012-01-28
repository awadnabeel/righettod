package com.drighetto.essai.axis2.services;

import com.dom.PersonCompiled;

/**
 * Simple web service
 * 
 * @author Dominique RIGHETTO<br>
 *         22 avr. 07<br>
 */
@SuppressWarnings("boxing")
public class SimpleService {

	/**
	 * Simple message service
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         22 avr. 07<br>
	 * @param msg
	 *            Message
	 * @return Message
	 */
	public String writeMessage(String msg) {
		return "Hello ".concat(msg);
	}

	/**
	 * Simple message service
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         4 mai 07<br>
	 * @param p
	 * @return String data
	 */
	public String getPersonDataV1(Person p) {
		return "NAME : " + p.getName() + " - AGE : " + p.getAge();
	}

	/**
	 * Simple message service
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         4 mai 07<br>
	 * @param pc
	 * @return String data
	 */
	public String getPersonDataV2(PersonCompiled pc) {
		return "NAME : " + pc.getName() + " - AGE : " + pc.getAge();
	}

	/**
	 * Simple message service
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         4 mai 07<br>
	 * @param p
	 * @return String data
	 */
	public PersonCompiled getPersonCompiled(Person p) {
		return new PersonCompiled(p.getName(), p.getAge());
	}

	/**
	 * Simple message service
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         4 mai 07<br>
	 * @param pc
	 * @return String data
	 */
	public Person getPerson(PersonCompiled pc) {
		return new Person(pc.getName(), pc.getAge());
	}

}
