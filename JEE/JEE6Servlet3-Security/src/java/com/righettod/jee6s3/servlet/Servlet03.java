package com.righettod.jee6s3.servlet;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;

/**
 * Sample servlet used to show programmatic servlet security declaration using JEE6 annotations.
 * 
 * 
 * This sample use logic role named "R2".
 * "R2" is required to perform all requests on this servlet .
 * 
 * Sample test command using CURL : "curl -X [HTTP_METHOD_NAME] --user [LOGIN]:[PASSWORD] http://localhost:8080/JEE6Servlet3-Security/Servlet03"
 *                                  "curl -X PUT --user TestUser01:password http://localhost:8080/JEE6Servlet3-Security/Servlet03"
 * 
 */
@WebServlet("/Servlet03")
@ServletSecurity(value =
@HttpConstraint(rolesAllowed = {"R2"}, transportGuarantee = TransportGuarantee.NONE))
public class Servlet03 extends BaseServlet {
}
