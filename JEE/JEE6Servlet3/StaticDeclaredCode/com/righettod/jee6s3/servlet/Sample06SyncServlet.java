package com.righettod.jee6s3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Sample synchronous servlet using deployment descriptor to declare it.<br>
 * <br>
 * This servlet show behavior of successive configuration beginning from
 * "web.xml" and enhanced in differents "web-fragment.xml" modules...
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("serial")
public class Sample06SyncServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain");
		// Servlet init parameter "msg00" is defined in web.xml
		// Servlet init parameter "msg01" is defined in web fragment n°01
		// Servlet init parameter "msg02" is defined in web fragment n°02
		// Servlet init parameter "msg03" is defined in web fragment n°03
		String msg = getInitParameter("msg00") + " " + getInitParameter("msg01") + " " + getInitParameter("msg02") + " " + getInitParameter("msg03");
		resp.getWriter().print(msg);
	}

}
