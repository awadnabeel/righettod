package com.righettod.jee6s3.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Base servlet defining "hello world" methods for HTTP METHOD types.
 * 
 * @author Dominique Righetto (dominique.righetto@logica.com)
 */
public class BaseServlet extends HttpServlet {

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("==> We receive a HTTP " + request.getMethod() + " request.");
        response.getWriter().printf("Hello '%s', is a response from HTTP GET method from '%s' ;o)", request.getRemoteUser(), this.getClass().getName());
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("==> We receive a HTTP " + request.getMethod() + " request.");
        response.getWriter().printf("Hello '%s', is a response from HTTP POST method from '%s' ;o)", request.getRemoteUser(), this.getClass().getName());
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("==> We receive a HTTP " + request.getMethod() + " request.");
        response.getWriter().printf("Hello '%s', is a response from HTTP PUT method from '%s' ;o)", request.getRemoteUser(), this.getClass().getName());
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("==> We receive a HTTP " + request.getMethod() + " request.");
        response.getWriter().printf("Hello '%s', is a response from HTTP DELETE method from '%s' ;o)", request.getRemoteUser(), this.getClass().getName());
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("==> We receive a HTTP " + request.getMethod() + " request.");
        response.getWriter().printf("Hello '%s', is a response from HTTP HEAD method from '%s' ;o)", request.getRemoteUser(), this.getClass().getName());
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("==> We receive a HTTP " + request.getMethod() + " request.");
        response.getWriter().printf("Hello '%s', is a response from HTTP TRACE method from '%s' ;o)", request.getRemoteUser(), this.getClass().getName());
    }
}
