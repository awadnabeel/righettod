package com.drighetto.jse6ehs;

import com.sun.net.httpserver.BasicAuthenticator;

/**
 * Simple implementation of a BASIC HTTP authenticator based on the
 * BasicAuthenticator impl. provided by SUN
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class SimpleBasicAuthenticator extends BasicAuthenticator {

	/**
	 * Constructor
	 * 
	 * @param realm Realm name
	 */
	public SimpleBasicAuthenticator(String realm) {
		super(realm);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.sun.net.httpserver.BasicAuthenticator#checkCredentials(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public boolean checkCredentials(String login, String password) {
		boolean isAuthenticated = false;

		// Perform the authentication with the login/password provided by the
		// user and return a flag indicating is the user is authenticated or
		// not...
		if ("dom".equals(login) && "dom".equals(password)) {
			isAuthenticated = true;
		}

		return isAuthenticated;
	}

}
