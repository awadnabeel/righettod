package com.drighetto.jse6ehs;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Simple HTTP request handler displaying request properties in the response
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class SimpleHttpHandler implements HttpHandler {

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.sun.net.httpserver.HttpHandler#handle(com.sun.net.httpserver.HttpExchange)
	 */
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		StringBuilder responseContent = new StringBuilder("");
		int responseCode = 200;
		/* Display request properties in the response */
		try {
			// Generate response content
			responseContent.append("*** REQUEST PROPERTIES ***<br />");
			responseContent.append("PROTOCOL       : ").append(httpExchange.getProtocol()).append("<br />");
			responseContent.append("URI            : ").append(httpExchange.getRequestURI()).append("<br />");
			responseContent.append("METHOD         : ").append(httpExchange.getRequestMethod()).append("<br />");
			responseContent.append("REMOTE ADDRESS : ").append(httpExchange.getRemoteAddress().getHostName()).append(
					"<br />");
			if (httpExchange.getPrincipal() != null) {
				responseContent.append("USERNAME       : ").append(httpExchange.getPrincipal().getUsername()).append(
						"<br />");
				responseContent.append("REALM          : ").append(httpExchange.getPrincipal().getRealm()).append(
						"<br />");
				responseContent.append("NAME           : ").append(httpExchange.getPrincipal().getName()).append(
						"<br />");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseContent.delete(0, responseContent.length());
			responseContent.append("ERROR : ").append(e.getMessage()).append("<br />");
			responseCode = 500;
		} finally {
			// Set response content type header
			httpExchange.getResponseHeaders().add("Content-Type", "text/html");
			// Send HTTP response headers,HTTP response code, HTTP response
			// content back to the client
			httpExchange.sendResponseHeaders(responseCode, responseContent.length());
			httpExchange.getResponseBody().write(responseContent.toString().getBytes());
			// Close the exchange (close the request and response streams)
			httpExchange.close();
		}
	}

}
