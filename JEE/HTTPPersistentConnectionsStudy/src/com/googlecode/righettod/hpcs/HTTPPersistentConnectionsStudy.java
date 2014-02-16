package com.googlecode.righettod.hpcs;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Simple class to study the behavior of the "Keep-Alive" HTTP request header with the JavaSE HTTP stack.<br>
 * This sample show also how to get multiple HTTP exchange using the same TCP stream.<br>
 * <br>
 * Wireshark filter:<br>
 * <code>http.request and http.host eq "www.righettod.eu"</code>
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * @see "http://docs.oracle.com/javase/7/docs/technotes/guides/net/http-keepalive.html"
 * @see "http://mttkay.github.io/blog/2013/03/02/herding-http-requests-or-why-your-keep-alive-connection-may-be-dead/"
 * 
 */
public class HTTPPersistentConnectionsStudy {

	private static final String TARGET = "http://www.righettod.eu";

	/**
	 * Entry point
	 * 
	 * @param args Command line
	 * @throws Exception
	 */
	@SuppressWarnings("boxing")
	public static void main(String[] args) throws Exception {
		HttpURLConnection connection = null;
		URL url = new URL(TARGET);
		int rc = 0;
		for (int i = 1; i <= 3; i++) {
			try {
				connection = (HttpURLConnection) url.openConnection();
				connection.connect();
				rc = connection.getResponseCode();
				System.out.printf("RQ[%s] => RC[%s]\n", i, rc);
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
			finally {
				if (connection != null) {
					try {
						// Important: Mark the end of the current HTTP request/response exchange, see link above for details...
						connection.getInputStream().close();
					}
					catch (IOException ioe2) {
						ioe2.printStackTrace();
					}
				}
			}
		}
		// Important: mark TCP stream as releasable
		if (connection != null) {
			connection.disconnect();
		}
	}
}
