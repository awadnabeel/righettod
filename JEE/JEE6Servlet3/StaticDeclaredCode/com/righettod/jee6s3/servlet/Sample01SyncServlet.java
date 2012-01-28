package com.righettod.jee6s3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Sample synchronous servlet using JEE6 annotation to declare it
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("serial")
@WebServlet(name = "Sample01SyncServlet", value = "/Sample01SyncServlet", asyncSupported = false, initParams = { @WebInitParam(name = "msg01", value = "Hello "),
		@WebInitParam(name = "msg02", value = " World!") })
public class Sample01SyncServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain");
		resp.getWriter().print(getInitParameter("msg01") + getInitParameter("msg02"));
	}

}
