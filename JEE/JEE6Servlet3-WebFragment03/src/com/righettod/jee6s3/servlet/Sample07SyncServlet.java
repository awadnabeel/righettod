package com.righettod.jee6s3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Sample synchronous servlet using deployment descriptor to declare it.<br>
 * <br>
 * This servlet show successive additivity configuration over main app.
 * (web.xml) and web fragments modules (web-fragment.xml)
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("serial")
public class Sample07SyncServlet extends HttpServlet {
	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain");
		// Servlet context parameter "param" is defined in web.xml
		// Servlet context parameter "param00" is defined in web.xml
		// Servlet context parameter "param01" is defined in web fragment n°01
		// Servlet context parameter "param02" is defined in web fragment n°02
		// Servlet context parameter "param03" is defined in web fragment n°03
		String msg = getServletContext().getInitParameter("param") + " " + getServletContext().getInitParameter("param00") + " " + getServletContext().getInitParameter("param01") + " "
				+ getServletContext().getInitParameter("param02") + " " + getServletContext().getInitParameter("param03");
		resp.getWriter().print(msg);
	}
}
