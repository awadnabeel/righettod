package com.drighetto.jaxrs.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * This exception is a custom exception that extends WebApplicationException
 * and builds a HTTP response with the 404 status code and an optional message
 * as the body of the response
 *
 * Copied and enhanced from : http://wikis.sun.com/display/Jersey/Overview+of+JAX-RS+1.0+Features
 *
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 */
public class CustomNotFoundException extends WebApplicationException {

    /**
     * Create a HTTP 404 (Not Found) exception.
     */
    public CustomNotFoundException() {
        super(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * Create a HTTP 404 (Not Found) exception.
     * @param message the String that is the entity of the 404 response.
     */
    public CustomNotFoundException(String message) {
        super(Response.status(Response.Status.NOT_FOUND).
                entity(message).type(MediaType.TEXT_PLAIN).build());
    }
}

