package com.righettod.swagger.jaxrs.application;

import com.sun.jersey.api.core.PackagesResourceConfig;
import javax.ws.rs.ApplicationPath;

/**
 * JAX-RS application definition for JEE6 container
 * 
 * @author Dominique Righetto
 */
//@ApplicationPath("/*")
public class MyApplication extends PackagesResourceConfig {
    
    /**
     * Constructor: define package to scan to find JAX-RS resources
     */
     public MyApplication() {
           super("com.righettod.swagger.jaxrs.resource","com.wordnik.swagger.jaxrs","com.righettod.swagger.jaxrs.provider");
       }    
}
