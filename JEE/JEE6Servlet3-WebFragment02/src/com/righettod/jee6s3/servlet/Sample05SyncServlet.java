package com.righettod.jee6s3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Sample synchronous servlet using JEE6 annotations to declare it
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("serial")
@WebServlet(name = "Sample05SyncServlet", value = "/Sample05SyncServlet", asyncSupported = false)
public class Sample05SyncServlet extends HttpServlet {
	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain");
		resp.getWriter().print("Hello world from 'Sample05SyncServlet' defined in web fragment 02 ;o)");
	}
}
