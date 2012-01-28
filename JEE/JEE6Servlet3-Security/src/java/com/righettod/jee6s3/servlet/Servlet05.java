package com.righettod.jee6s3.servlet;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;

/**
 * Sample servlet used to show programmatic AND declarative servlet security declaration using JEE6 annotations and deployment descriptor (DD).
 * 
 * This time we wil show cumulative security configuration applied to a same URL PATTERN using annotation and web deployment descriptor (DD).
 * 
 * Using annotation we define security constraints below:
 * 1. This servlet use logic role named "R2".
 * 2. "R2" is required to perform all requests on this servlet.
 * 3. The transport is not secured.
 *  
 * The security configuration for a specified URL PATTERN defined with annotation is overrided by the configuration into the DD !!!
 * 
 */
@WebServlet("/Servlet05")
@ServletSecurity(value =
@HttpConstraint(rolesAllowed = {"R2"}, transportGuarantee = TransportGuarantee.NONE))
public class Servlet05 extends BaseServlet {
}
