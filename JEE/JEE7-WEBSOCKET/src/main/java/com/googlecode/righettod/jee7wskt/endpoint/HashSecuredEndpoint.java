package com.googlecode.righettod.jee7wskt.endpoint;

import com.googlecode.righettod.jee7wskt.endpoint.encdec.HashExchangeDecoder;
import com.googlecode.righettod.jee7wskt.endpoint.encdec.HashExchangeEncoder;
import com.googlecode.righettod.jee7wskt.vo.HashExchange;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.annotation.security.RolesAllowed;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Secured version of the HashBasicEndpoint endpoint.
 *
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 */
@ServerEndpoint(value = "/secured/hash", encoders = {HashExchangeEncoder.class}, decoders = {HashExchangeDecoder.class}, configurator = CustomEndpointConfigurator.class)
@RolesAllowed("tomcat_role")
public class HashSecuredEndpoint extends HashBasicEndpoint {

    /**
     * Constructor
     */
    public HashSecuredEndpoint() {
        System.out.println(">>>> Create new instance of HashSecuredEndpoint endpoint.");
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
    @Override
    @OnMessage
    public void onMessageThroughCustomObject(Session session, HashExchange heMsg) throws NoSuchAlgorithmException, IOException, EncodeException {
        //We do not have access to user role at this point then we must get them during handshake
        System.out.printf(">> Call of secured version of the endpoint from %s user with role 'tomcat_role' (%s)%n", session.getUserPrincipal().getName(), session.getUserProperties().get("HAS_TC_ROLE"));
        heMsg.setFromSecuredHandler(true);
        super.onMessageThroughCustomObject(session, heMsg);
        
    }
    
}
