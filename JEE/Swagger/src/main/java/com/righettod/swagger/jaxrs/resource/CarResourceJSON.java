package com.righettod.swagger.jaxrs.resource;

import com.sun.jersey.spi.resource.Singleton;
import com.wordnik.swagger.core.Api;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Class providing operations (by inheritance) on a Car resource and manage JSON data entity representation.
 * 
 * @author Dominique Righetto
 */
@Path("/car.json")
@Api(value = "/car", description = "Operations about car")
@Singleton
@Produces({MediaType.APPLICATION_JSON})
public class CarResourceJSON extends CarResource{}
