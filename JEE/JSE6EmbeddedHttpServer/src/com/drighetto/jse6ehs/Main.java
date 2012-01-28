package com.drighetto.jse6ehs;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

/**
 * Entry point that create,configure and start a EHS on the current machine on
 * port 8123 <br>
 * <br>
 * <b>EHS = Embedded Http Server</b>
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class Main {

	/**
	 * Entry point
	 * 
	 * @param args Command line
	 */
	public static void main(String[] args) {
		try {
			// Create a EHS on the current machine on port 8123
			HttpServer ehs = HttpServer.create(new InetSocketAddress(InetAddress.getLocalHost(), 8123), 50);
			// Add a context associating a handler with a URI pattern
			// See URL below for details about handler and URI pattern :
			// http://java.sun.com/javase/6/docs/jre/api/net/httpserver/spec/com/sun/net/httpserver/HttpServer.html
			HttpContext ctx = ehs.createContext("/jse6ehs", new SimpleHttpHandler());
			// Add a authenticator to the context
			ctx.setAuthenticator(new SimpleBasicAuthenticator("JSE6EHS Realm"));
			// Add filters to the context
			ctx.getFilters().add(new SimpleFilter("Filter01"));
			ctx.getFilters().add(new SimpleFilter("Filter02"));
			ctx.getFilters().add(new SimpleFilter("Filter03"));
			// Start the server
			ehs.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
