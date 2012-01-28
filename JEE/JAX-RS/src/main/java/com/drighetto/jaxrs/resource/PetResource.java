package com.drighetto.jaxrs.resource;

import com.drighetto.jaxrs.exception.CustomNotFoundException;
import com.drighetto.jaxrs.representation.PetBean;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Class representing a Pet Resource in REST terminology
 * <br>
 * This class use CURL commands to test each URI : http://curl.haxx.se/
 *
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 *
 * <br>
 *
 * The base URI of this resource is "(SERVER_URI)/pet"
 */
@Path("pet")
public class PetResource {

    /**Private map used as memory DB to store Pet instances*/
    private static final Map<String, PetBean> PET_STORE = new Hashtable<String, PetBean>();


    static {
        //Add some sample data in db....
        PET_STORE.put("Snoppy", new PetBean("Snoppy", 2));
        PET_STORE.put("Droppy", new PetBean("Droppy", 5));
        PET_STORE.put("Pluto", new PetBean("Pluto", 10));
        PET_STORE.put("DefaultPet", new PetBean("DefaultPet", 15));
    }

    /**
     * Method to obtains a pet from is name
     * @param name pet name
     * @return a pet bean
     * <br>
     * <br>
     * The annotation below indicates that we accept a GET request to the
     * URI "(SERVER_URI)/pet/{name}" and we return a XML response with Pet
     * informations
     * <br>
     * CURL test command :<br>
     * curl -H "Accept: application/xml" http://localhost:8080/JAX-RS-Sample/pet/Pluto
     */
    @Path("{name}")
    @GET
    @Produces("application/xml")
    public PetBean getPetFromPathParam(@PathParam("name") String name) {
        //Retrieve Pet from map
        if (PET_STORE.containsKey(name)) {
            return PET_STORE.get(name);
        } else {
            //Throw a custom mapped exception
            throw new CustomNotFoundException();
        }
    }

    /**
     * Method to obtains a pet from is name
     * @param name pet name
     * @return a pet bean
     * <br>
     * <br>
     * The annotation below indicates that we accept a GET request to the
     * URI "(SERVER_URI)/pet?name=PET_NAME" and we return a XML response with Pet
     * informations<br>
     * In this version we use HTTP parameter (in the URI) instead of URI path to pass the name of the pet<br>
     * We specify also with "@DefaultValue" that if not parameter is passed the default value
     * for the parameter is set to "DefaultPet"
     * <br>
     * CURL test commands :<br>
     * curl -H "Accept: application/xml" http://localhost:8080/JAX-RS-Sample/pet?name=Pluto
     * curl -H "Accept: application/xml" http://localhost:8080/JAX-RS-Sample/pet
     */
    @GET
    @Produces("application/xml")
    public PetBean getPetFromQueryParam(@DefaultValue("DefaultPet") @QueryParam("name") String name) {
        //Retrieve Pet from map
        if (PET_STORE.containsKey(name)) {
            return PET_STORE.get(name);
        } else {
            //Throw a custom mapped exception
            throw new CustomNotFoundException();
        }
    }

    /**
     * Method to obtains a pet from is name
     * @param name pet name
     * @return a pet bean
     * <br>
     * <br>
     * The annotation below indicates that we accept a GET request to the
     * URI "(SERVER_URI)/pet/header" and we return a XML response with Pet
     * informations<br>
     * In this version we use HTTP request header instead of URI path or HTTP parameter to pass the name of the pet
     * <br>
     * CURL test command :<br>
     * curl -H "Accept: application/xml" -H "PetName: Snoppy" http://localhost:8080/JAX-RS-Sample/pet/header
     */
    @Path("/header")
    @GET
    @Produces("application/xml")
    public PetBean getPetFromHeaderParam(@HeaderParam("PetName") String name) {
        //Retrieve Pet from map
        if (PET_STORE.containsKey(name)) {
            return PET_STORE.get(name);
        } else {
            //Throw a custom mapped exception
            throw new CustomNotFoundException();
        }
    }

    /**
     * Method to create a new Pet
     * @param pet Pet to create
     * @return A HTTP response object
     * <br>
     * <br>
     * The annotation below indicates that we accept a PUT request to the
     * URI "(SERVER_URI)/pet" with a embedded XML Representation representing Pet data
     * and we return a HTTP response to validate the processing
     * <br>
     * CURL test command :<br>
     * curl -X PUT --data-binary "<pet><petName>RogerXML</petName><petAge>4</petAge></pet>" -H "Content-Type: application/xml" http://localhost:8080/JAX-RS-Sample/pet
     */
    @PUT
    @Consumes("application/xml")
    public Response putPet(PetBean pet) {
        //Check input data
        if (pet == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        //Add pet to store
        PET_STORE.put(pet.getPetNameValue(), pet);
        //Create ans return response
        return Response.ok("Pet correctly created !").build();
    }

    /**
     * Method to search a pet using a HTML FORMS
     * @param name name of the Pet
     * @return a HTTP response object with a entity embedded that is the Pet informations 
     * <br>
     * <br>
     * The annotation below indicates that we accept a POST request to the
     * URI "(SERVER_URI)/pet" with a a HTTP parameter (in the request body) named "name" sended by a HTML FORMS
     * and we return a HTTP response with a entity embedded that is the Pet informations
     * <br>
     * CURL test command :<br>
     * curl -d name=Pluto http://localhost:8080/JAX-RS-Sample/pet
     */
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response postPetFromForm(@FormParam("name") String name) {
        //Check input data
        if (name == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        //Find Pet
        if (PET_STORE.containsKey(name)) {
            //Create and return response with the Pet informations as reponse entity
            return Response.ok(PET_STORE.get(name), MediaType.APPLICATION_XML).build();
        } else {
            //Create and return empty response
            return Response.notModified().build();
        }
    }

    /**
     * Method to delete a pet
     * @param name Name of the pet to delete
     * @return a HTTP response object
     * <br>
     * <br>
     * The annotation below indicates that we accept a DELETE request to the
     * URI "(SERVER_URI)/pet/{name}" and we return a HTTP response to validate the processing
     * <br>
     * CURL test command :<br>
     * curl -X DELETE http://localhost:8080/JAX-RS-Sample/pet/Pluto
     */
    @Path("{name}")
    @DELETE
    public Response deletePet(@PathParam("name") String name) {
        //Check input data
        if (name == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        //Remove pet if exits
        if (PET_STORE.containsKey(name)) {
            //Remove pet
            PET_STORE.remove(name);
            //Create and return response
            return Response.ok("Pet correctly removed !").build();
        } else {
            //Create and return response
            return Response.notModified("Pet not found !").build();
        }
    }

    /**
     * Method to obtains request informations
     *
     * @param ui URI Informations (provided by injection from the web container)
     * @param hh Request headers (provided by injection from the web container)
     * @return a HTTP response with some request informations
     *
     * <br>
     *
     * The annotation below indicates that we accept a GET request to the
     * URI "(SERVER_URI)/pet/request-infos" and we return a HTTP response with some request informations
     * <br>
     * CURL test command :<br>
     * curl -v http://localhost:8080/JAX-RS-Sample/pet/request-infos
     */
    @GET
    @Path("request-infos")
    public Response getRequestInfos(@Context UriInfo ui, @Context HttpHeaders hh) {
        String data = "REQUEST PATH = " + ui.getPath() + "\nREQUEST HEADERS =  " + hh.getRequestHeaders();
        return Response.ok(data, MediaType.TEXT_PLAIN).build();
    }

    /**
     * Method to process a FORMS without any knowledge of the parameters sent
     *
     * @param formParams Forms parameters
     * @return a HTTP response with the parameters sent by the Forms
     *
     * <br>
     *
     * The annotation below indicates that we accept a POST request sent by a FORMS to the
     * URI "(SERVER_URI)/pet/forms" and we return a HTTP response with the parameters sent by the Forms
     * <br>
     * CURL test command :<br>
     * curl -v -d Param1=1 -d Param2=2 http://localhost:8080/JAX-RS-Sample/pet/forms
     */
    @POST
    @Path("forms")
    @Consumes("application/x-www-form-urlencoded")
    public Response postForm(MultivaluedMap<String, String> formParams) {
        String data = "";
        if (formParams != null) {
            for (Map.Entry<String, List<String>> entry : formParams.entrySet()) {
                data += entry.getKey() + "=" + entry.getValue() + "\n";
            }
        }
        return Response.ok(data, MediaType.TEXT_PLAIN).build();
    }
}
