package com.righettod.swagger.jaxrs.resource;

import com.sun.jersey.spi.resource.Singleton;
import com.wordnik.swagger.core.Api;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Class provding operations (by inheritance) on a Car resource and manage XML data entity representation.
 * 
 * @author Dominique Righetto
 */
@Path("/car.xml")
@Api(value = "/car", description = "Operations about car")
@Singleton
@Produces({MediaType.APPLICATION_XML})
public class CarResourceXML extends CarResource{}
