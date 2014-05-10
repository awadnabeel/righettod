package com.googlecode.righettod.jee7wskt.endpoint;

import com.googlecode.righettod.jee7wskt.endpoint.encdec.HashExchangeDecoder;
import com.googlecode.righettod.jee7wskt.endpoint.encdec.HashExchangeEncoder;
import com.googlecode.righettod.jee7wskt.vo.HashExchange;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.PongMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.xml.bind.DatatypeConverter;

/**
 * Websocket endpoint providing HASH generation service. <br>
 * Use annoted sytle because it's more simpler and avoid boilerplate setup code.<br>
 *
 * We cannot define a handler taking raw String as input because we already use a handler that use a custom value object for which encoding/decoding <br>
 * (string from/to object) is managed by custom Encoder/Decoder implementations. See details below (not allowed by specification):<br>
 * http://stackoverflow.com/a/22090459/451455
 *
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * @see "http://docs.oracle.com/javaee/7/tutorial/doc/websocket004.htm#BABFEBGA"
 * @see "http://docs.oracle.com/javaee/7/tutorial/doc/websocket005.htm#sthref1274"
 * @see "http://www.whatwg.org/specs/web-apps/current-work/multipage/network.html#ping-and-pong-frames"
 */
@ServerEndpoint(value = "/hash", encoders = {HashExchangeEncoder.class}, decoders = {HashExchangeDecoder.class}, configurator = CustomEndpointConfigurator.class)
public class HashBasicEndpoint {

    /**
     * Constructor
     */
    public HashBasicEndpoint() {
        System.out.println(">>>> Create new instance of HashBasicEndpoint endpoint.");
    }

    /**
     * Method invoked when a connection is created with endpoint.
     *
     * @param session Communication session with client.
     * @param conf Endpoint configuratin.
     */
    @OnOpen
    public void open(Session session, EndpointConfig conf) {
        System.out.printf(">>>> Session %s is now opened%n", session.getId());
    }

    /**
     * Method invoked when there are connection problems, runtime errors from message handlers, <br>
     * or conversion errors when decoding messages.
     *
     * @param session Communication session with client.
     * @param error Error details.
     */
    @OnError
    public void error(Session session, Throwable error) {
        System.err.printf(">>>> Error occur in session %s: %s%n", session.getId(), error.getMessage());
    }

    /**
     * Method invoked when one part close the communication.
     *
     * @param session Communication session with client.
     * @param reason Details about communication ending.
     */
    @OnClose
    public void close(Session session, CloseReason reason) {
        System.out.printf("<<<< Session %s is now closed: %s (%s)%n", session.getId(), reason.getReasonPhrase(), reason.getCloseCode().getCode());
    }

    /**
     * Method processing incoming message using value object to transmit information.
     *
     * @param session Communication session with client.
     * @param heMsg Incoming value object containing exchange data (automatically encoded/decoder by the framework using encoder/decoder specified on endpoint definition)
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.IOException
     * @throws javax.websocket.EncodeException
     */
    @OnMessage
    public void onMessageThroughCustomObject(Session session, HashExchange heMsg) throws NoSuchAlgorithmException, IOException, EncodeException {
        System.out.println(">> Received request as HashExchange object...");
        //Generate hash based on provided infos
        MessageDigest md = MessageDigest.getInstance(heMsg.getAlgorithm());
        byte[] digest = md.digest(heMsg.getOriginalData().getBytes());
        heMsg.setHashedData(DatatypeConverter.printBase64Binary(digest));
        //Send response
        session.getBasicRemote().sendObject(heMsg);
        System.out.println("<< Response sent as HashExchange object.");
        //Count message sent to this client
        if (!session.getUserProperties().containsKey("COUNT")) {
            session.getUserProperties().put("COUNT", 1);
        } else {
            Integer counter = (Integer) session.getUserProperties().get("COUNT");
            session.getUserProperties().put("COUNT", ++counter);
        }
        System.out.printf("<< %s exchange with session %s%n", (Integer) session.getUserProperties().get("COUNT"), session.getId());
    }

    /**
     * Method processing PING incoming message type.
     *
     * @param session Communication session with client.
     * @param msg Ping request.
     * @throws java.io.IOException
     */
    @OnMessage
    public void onMessageThroughPong(Session session, PongMessage msg) throws IOException {
        //Answer to client that this endpoint is OK
        session.getBasicRemote().sendText("Endpoint is OK");
    }

    /**
     * Method processing incoming message using ByteBuffer to transmit information.
     *
     * @param session Communication session with client.
     * @param msg Incoming object containing exchange data.
     * @throws java.io.IOException
     */
    @OnMessage
    public void onMessageThroughBinary(Session session, ByteBuffer msg) throws IOException {
        //Answer to client that this format is not supported
        session.getBasicRemote().sendText("Message format not supported !");
    }

}
