package com.righettod.websocket.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.websocket.MessageInbound;

import com.google.gson.Gson;

/**
 * Message handler for WebSocket inbound request (message).
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 * @see "http://tomcat.apache.org/tomcat-7.0-doc/web-socket-howto.html"
 * @see "http://tomcat.apache.org/tomcat-7.0-doc/api/org/apache/catalina/websocket/MessageInbound.html"
 * 
 */
@SuppressWarnings("boxing")
public class MessageHandler extends MessageInbound {

	/** Internal message storage to create stats for received messages */
	private static final Map<String, Integer> RECEIVED_MESSAGES_STATS = Collections.synchronizedMap(new HashMap<String, Integer>());

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.apache.catalina.websocket.MessageInbound#onBinaryMessage(java.nio.ByteBuffer)
	 */
	@Override
	protected void onBinaryMessage(ByteBuffer message) throws IOException {
		throw new IOException("Binary message type is not supported !");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.apache.catalina.websocket.MessageInbound#onTextMessage(java.nio.CharBuffer)
	 */
	@Override
	protected void onTextMessage(CharBuffer message) throws IOException {
		// Extract message sender identifier
		String id = message.toString();
		// Update stats for sender
		if (!RECEIVED_MESSAGES_STATS.containsKey(id)) {
			RECEIVED_MESSAGES_STATS.put(id, 0);
		}
		Integer counter = RECEIVED_MESSAGES_STATS.get(id);
		counter++;
		RECEIVED_MESSAGES_STATS.put(id, counter);
		// Send response to sender
		String json = new Gson().toJson(RECEIVED_MESSAGES_STATS);
		CharBuffer response = CharBuffer.wrap(json);
		getWsOutbound().writeTextMessage(response);
	}

}
