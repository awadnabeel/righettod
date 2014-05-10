package com.googlecode.righettod.jee7wskt.endpoint;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * Custom class to act on Endpoint configuration.
 *
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * @see "http://docs.oracle.com/javaee/7/tutorial/doc/websocket010.htm#BABJAIGH"
 */
public class CustomEndpointConfigurator extends ServerEndpointConfig.Configurator {

    /**
     * Called by the container after it has formulated a handshake response resulting from a well-formed handshake request. <br>
     * The container has already checked that this configuration has a matching URI, determined the validity of the origin using <br>
     * the checkOrigin method, and filled out the negotiated subprotocols and extensions based on this configuration. <br>
     * Custom configurations may override this method in order to inspect the request parameters and modify the handshake response <br>
     * that the server has formulated. and the URI checking also.<br><br>
     *
     *
     * If the developer does not override this method, no further modification of the request and response are made by the implementation.
     *
     * @param conf The configuration object involved in the handshake.
     * @param request The opening handshake request.
     * @param response The proposed opening handshake response.
     */
    @Override
    public void modifyHandshake(ServerEndpointConfig conf, HandshakeRequest request, HandshakeResponse response) {
        for (String header : request.getHeaders().keySet()) {
            System.out.printf(">>>> Handshake request header '%s' => %s%n", header, request.getHeaders().get(header));
        }
        for (String header : response.getHeaders().keySet()) {
            System.out.printf("<<<< Handshake response header '%s' => %s%n", header, response.getHeaders().get(header));
        }
    }

    /**
     * Check the value of the Origin header (See Origin Header) the client passed during the opening handshake.
     *
     * @param originHeaderValue the value of the origin header passed by the client.
     * @return whether the check passed or not
     */
    @Override
    public boolean checkOrigin(String originHeaderValue) {
        System.out.printf(">>>> '%s' origin specified by the client during handshake.%n", originHeaderValue);
        return true;
    }

}
