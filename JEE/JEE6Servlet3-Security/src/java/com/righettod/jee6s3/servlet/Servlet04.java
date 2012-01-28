package com.righettod.jee6s3.servlet;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;

/**
 * Sample servlet used to show programmatic servlet security declaration using JEE6 annotations.
 * 
 * 
 * This sample use logic role named "R1".
 * "R1" is required to perform all requests on this servlet .
 * 
 * This time we required that the request come using a secure protocol (HTTP/S) using "TransportGuarantee.CONFIDENTIAL" instruction.
 * 
 * Sample test command using CURL : 
 * "curl --insecure -L -X [HTTP_METHOD_NAME] --user [LOGIN]:[PASSWORD] http://localhost:8080/JEE6Servlet3-Security/Servlet04"
 * "curl --insecure -L -X GET --user TestUser01:password http://localhost:8080/JEE6Servlet3-Security/Servlet04"
 * 
 * "-L"         : Curl option is used to follow redirection send by the container in order to redirect the initial resquest to a secure channel.
 * "--insecure" : Curl option is used to ignore untrusted server side SSL certificate. 
 * 
 */
@WebServlet("/Servlet04")
@ServletSecurity(value =
@HttpConstraint(rolesAllowed = {"R1"}, transportGuarantee = TransportGuarantee.CONFIDENTIAL))
public class Servlet04 extends BaseServlet {
}
