package com.googlecode.righettod.jee7wskt.endpoint.encdec;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.googlecode.righettod.jee7wskt.vo.HashExchange;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Encoder to takes JSON representation and produces a Java object.
 *
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * @see "http://docs.oracle.com/javaee/7/tutorial/doc/websocket007.htm#sthref1277"
 */
public class HashExchangeDecoder implements Decoder.Text<HashExchange> {

    /**
     * JSON encoding API
     */
    private Gson decoder = null;

    /**
     * Manage decoding.
     *
     * @param s String to decode (JSON format here).
     * @return Java object.
     * @throws DecodeException
     */
    @Override
    public HashExchange decode(String s) throws DecodeException {
        HashExchange he = null;
        try {
            he = this.decoder.fromJson(s, HashExchange.class);
        } catch (JsonSyntaxException jse) {
            System.err.printf(">> Cannot decode JSON representation provided: %s%n", jse.getMessage());
            throw new DecodeException(s, "Cannot decode JSON representation provided !", jse);
            //If an DecodeException is throw by this method then the endpoint handler is not called even the 'error' method...
            //It's strange because, according to Javadoc of the error method, for a WebSocket Endpoint it must be the case:
            //http://docs.oracle.com/javaee/7/api/javax/websocket/Endpoint.html
            //"... conversion errors encoding incoming messages before any message handler has been called. These are modeled as DecodeExceptions"
            //http://docs.oracle.com/javaee/7/tutorial/doc/websocket009.htm
        }

        return he;
    }

    /**
     * Answer whether the given String can be decoded into an object of target type.
     *
     * @param s String to decode (JSON format here).
     * @return Java object.
     */
    @Override
    public boolean willDecode(String s) {
        return true;
    }

    /**
     * Setup method.
     *
     * @param config Endpoint config.
     */
    @Override
    public void init(EndpointConfig config) {
        this.decoder = new Gson();
    }

    /**
     * Finalization method.
     */
    @Override
    public void destroy() {
        //Not used
    }

}
