package com.drighetto.spring25x.testing;

/**
 * Simple bean that will be tested....
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
class MyTestedBean {

	/**
	 * Simple method
	 * 
	 * @param param
	 *            input message
	 * @throws IllegalArgumentException
	 * @return a object
	 */
	@SuppressWarnings("boxing")
	Object sayHello(String param) {
		Object output = param;

		// if the input parameter is a String return a String otherwise
		// throw a IllegalArguementException
		try {
			Integer.parseInt(param);
			throw new IllegalArgumentException("param must be a String !");
		} catch (Exception e) {
			// No Message....
		}

		return output;

	}

}
