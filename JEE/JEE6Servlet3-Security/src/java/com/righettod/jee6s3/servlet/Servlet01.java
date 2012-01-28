package com.righettod.jee6s3.servlet;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.EmptyRoleSemantic;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;

/**
 * Sample servlet used to show programmatic servlet security declaration using JEE6 annotations.
 * 
 * 
 * This sample use logic role named "R1" and "R2".
 * "R1" is required to perform a "GET" request on this servlet and "R2" is required to perform a "POST" request on this servlet.
 * All others HTTP request type (HEAD/DELETE/PUT/....) are denied.
 * 
 * Sample test command using CURL : "curl -X [HTTP_METHOD_NAME] --user [LOGIN]:[PASSWORD] http://localhost:8080/JEE6Servlet3-Security/Servlet01"
 *                                  "curl -X DELETE --user TestUser02:password http://localhost:8080/JEE6Servlet3-Security/Servlet01"
 * 
 */
@WebServlet("/Servlet01")
@ServletSecurity(value =
@HttpConstraint(value = EmptyRoleSemantic.DENY, transportGuarantee = TransportGuarantee.NONE),
httpMethodConstraints = {
    @HttpMethodConstraint(value = "GET", rolesAllowed = {"R1"}, transportGuarantee = TransportGuarantee.NONE),
    @HttpMethodConstraint(value = "POST", rolesAllowed = {"R2"}, transportGuarantee = TransportGuarantee.NONE)
})
public class Servlet01 extends BaseServlet {
}
