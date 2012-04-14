package com.righettod.swagger.jaxrs.resource;

import com.righettod.swagger.jaxrs.entity.Car;
import com.wordnik.swagger.core.ApiError;
import com.wordnik.swagger.core.ApiErrors;
import com.wordnik.swagger.core.ApiOperation;
import com.wordnik.swagger.core.ApiParam;
import com.wordnik.swagger.jaxrs.JavaHelp;
import java.util.Hashtable;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Class providing operations on a Car resource.
 * <br />
 * 
 * This class only provides operations, resource sub classes manages REST representations exposition of entity returned if there are not simple string (plain text).
 * <br />
 * Entity is annotated with JAXB annotations to manage XML representation and a Jersey provider is registered to manage JSON representation based on JAXB annotation specified.
 * 
 * Sample CURL commands: <br /> 
 * <ul>
 * <li>ADD           : curl -X POST -d "id=123&name=A4&constructor=audi" http://localhost:8080/Swagger/car.xml</li>
 * <li>ADD           : curl -X POST -d "id=321&name=A6&constructor=audi" http://localhost:8080/Swagger/car.json</li>
 * <li>GET           : curl -X GET http://localhost:8080/Swagger/car.xml/123</li>
 * <li>GET           : curl -X GET http://localhost:8080/Swagger/car.json/321</li>
 * <li>DELETE        : curl -X DELETE http://localhost:8080/Swagger/car.xml/123</li>
 * <li>DELETE        : curl -X DELETE http://localhost:8080/Swagger/car.json/321</li>
 * </ul>
 * <ul>
 * <li>DOCUMENTATION : curl -X GET http://localhost:8080/Swagger/resources.xml</li>
 * <li>DOCUMENTATION : curl -X GET http://localhost:8080/Swagger/resources.json</li>
 * <li>DOCUMENTATION : curl -X GET http://localhost:8080/Swagger/car.xml</li>
 * <li>DOCUMENTATION : curl -X GET http://localhost:8080/Swagger/car.json</li>
 * </ul>
 *
 * Extending JavaHelp will "register" it in the swagger documentation system.<br />
 * 
 *
 * @author Dominique Righetto
 */
public class CarResource extends JavaHelp {

    /**
     * Car fake DB
     */
    private static final Map<String, Car> CAR_DB = new Hashtable<String, Car>();

    /**
     * Service to add a new car
     *
     * @param id Car ID
     * @param name Car name
     * @param constructor Car constuctor name
     */
    @POST
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(value = "Add a new car", notes = "Create new car into DB", responseClass = "javax.ws.rs.core.Response", tags = "CAR,ADD")
    @ApiErrors(value = {
        @ApiError(code = 400, reason = "Invalid Car ID supplied"),
        @ApiError(code = 500, reason = "Unexpected error")})
    public Response add(@ApiParam(required = true, allowMultiple = false, allowableValues = "Only number", value = "Car identifier") @FormParam("id") String id, @ApiParam(required = false, allowMultiple = false, allowableValues = "All unless space", value = "Car name") @FormParam("name") String name, @ApiParam(value = "Car constructor name", allowableValues = "opel, ford, audi") @FormParam("constructor") String constructor) {
        if (id == null || "".equals(id.trim())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Car c = new Car();
        c.id = id;
        c.name = name;
        c.constructor = constructor;
        CAR_DB.put(c.id, c);
        return Response.ok("Car added", MediaType.TEXT_PLAIN_TYPE).build();
    }

    /**
     * Service to retrieve car info.<br />
     * 
     * Produced format depend on user request and is managed by sub classes
     *
     * @param id Car ID
     */
    @Path("/{id}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @ApiOperation(value = "Get car info as XML", notes = "Retrieve car info from DB", responseClass = "com.righettod.swagger.jaxrs.entity.Car", tags = "CAR,GET")
    @ApiError(code = 500, reason = "Unexpected error")
    public Response retrieve(@ApiParam(required = true, allowMultiple = false, allowableValues = "Only number", value = "Car identifier") @PathParam("id") String id) {
        Car c = null;
        if (CAR_DB.containsKey(id)) {
            c = CAR_DB.get(id);
        } else {
            c =  new Car();
        }
        //Return Car Entity and let sub class manage representation
        return Response.ok(c).build();
    }


    /**
     * Service to remove car
     *
     * @param id Car ID
     */
    @Path("/{id}")
    @DELETE
    @ApiOperation(value = "Remove car info", notes = "Remove car info from DB", tags = "CAR,DELETE")
    @ApiError(code = 500, reason = "Unexpected error")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@ApiParam(required = true, allowMultiple = false, allowableValues = "Only number", value = "Car identifier") @PathParam("id") String id) {
        CAR_DB.remove(id);
        return Response.ok("Car removed", MediaType.TEXT_PLAIN_TYPE).build();
    }
}
