package com.righettod.websocket.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

import com.righettod.websocket.handler.MessageHandler;

/**
 * WebSocket dedicated servlet type to manage WebSocket Inbound/Outbound messages on server side.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 * @see "http://tomcat.apache.org/tomcat-7.0-doc/web-socket-howto.html"
 * @see "http://tomcat.apache.org/tomcat-7.0-doc/api/index.html?org/apache/catalina/websocket/WebSocketServlet.html"
 * 
 */
@WebServlet("/stats")
@SuppressWarnings("serial")
public class MessageWebSocketServlet extends WebSocketServlet {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.apache.catalina.websocket.WebSocketServlet#createWebSocketInbound(java.lang.String)
	 */
	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol) {
		System.out.printf("==> Create new message handler for sub protocol : '%s'\n", subProtocol);
		return new MessageHandler();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.apache.catalina.websocket.WebSocketServlet#verifyOrigin(java.lang.String)
	 */
	@Override
	protected boolean verifyOrigin(String origin) {
		// During my tests i have noticed that ORIGIN is obtained using the HTTP request header named 'Origin'.
		// If you alter it then the altered value is used here ;o)
		System.out.printf("==> Verify origin for : '%s'\n", origin);
		// No verification here....
		return true;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.apache.catalina.websocket.WebSocketServlet#selectSubProtocol(java.util.List)
	 */
	@Override
	protected String selectSubProtocol(List<String> subProtocols) {
		System.out.printf("==> Select sub protocol for sub protocols list : '%s'\n", subProtocols);
		// Return first protocol for sample or null if no protocol is specified...
		return ((subProtocols == null) || subProtocols.isEmpty()) ? null : subProtocols.get(0);
	}

}
