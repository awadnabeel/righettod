package com.righettod.jee6s3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Sample synchronous servlet that will be declared dynamically using JEE6 API.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("serial")
public class Sample03SyncServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession(true).setAttribute("MyAttribute", "MyValue");
		resp.setContentType("text/plain");
		resp.getWriter().print(getInitParameter("msg"));
	}
}
