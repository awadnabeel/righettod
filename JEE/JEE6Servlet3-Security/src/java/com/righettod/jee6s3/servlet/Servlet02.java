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
 * This sample use logic role named "R1".
 * "R1" is required to perform a "TRACE" request on this servlet .
 * All others HTTP request type (DELETE/GET/POST/....) are allowed.
 * 
 * Sample test command using CURL : "curl -X [HTTP_METHOD_NAME] --user [LOGIN]:[PASSWORD] http://localhost:8080/JEE6Servlet3-Security/Servlet02"
 *                                  "curl -X PUT --user TestUser01:password http://localhost:8080/JEE6Servlet3-Security/Servlet02"
 * 
 */
@WebServlet("/Servlet02")
@ServletSecurity(value =
@HttpConstraint(value = EmptyRoleSemantic.PERMIT, transportGuarantee = TransportGuarantee.NONE),
httpMethodConstraints = {
    @HttpMethodConstraint(value = "PUT", rolesAllowed = {"R1"}, transportGuarantee = TransportGuarantee.NONE)
})
public class Servlet02 extends BaseServlet {
}
