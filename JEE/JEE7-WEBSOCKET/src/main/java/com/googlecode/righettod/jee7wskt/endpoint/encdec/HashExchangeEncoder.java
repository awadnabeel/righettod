package com.googlecode.righettod.jee7wskt.endpoint.encdec;

import com.google.gson.Gson;
import com.googlecode.righettod.jee7wskt.vo.HashExchange;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Encoder to takes a Java object and produces a JSON representation.
 *
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * @see "http://docs.oracle.com/javaee/7/tutorial/doc/websocket007.htm#sthref1277"
 */
public class HashExchangeEncoder implements Encoder.Text<HashExchange> {

    /**
     * JSON encoding API
     */
    private Gson encoder = null;

    /**
     * Manage encoding.
     *
     * @param object
     * @return A JSON representation of the object.
     * @throws EncodeException
     */
    @Override
    public String encode(HashExchange object) throws EncodeException {
        return this.encoder.toJson(object);
    }

    /**
     * Setup method.
     *
     * @param config Endpoint config.
     */
    @Override
    public void init(EndpointConfig config) {
        this.encoder = new Gson();
    }

    /**
     * Finalization method.
     */
    @Override
    public void destroy() {
        //Not used
    }

}
