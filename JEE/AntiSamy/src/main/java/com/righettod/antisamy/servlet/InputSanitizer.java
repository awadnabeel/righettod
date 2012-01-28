package com.righettod.antisamy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;

/**
 * Servlet managing input sanitizing using AntiSamy API and scanning rules set
 * file "anti-samy-rules.xml"
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("serial")
@WebServlet("/sanitize")
public class InputSanitizer extends HttpServlet {

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder html = new StringBuilder("<html><body>");
		try {
			/* Step 1 : Load AntiSamy Policy rules file */
			Policy policy = Policy.getInstance(getClass().getResource("/anti-samy-rules.xml"));

			/* Step 2 : Sanitize input */
			// --Create AS object using policy loaded
			AntiSamy as = new AntiSamy(policy);
			// --Scan input data received
			CleanResults result = as.scan(req.getParameter("input"), AntiSamy.SAX);

			/* Step 3 : Return to user processing information of data received */
			html.append("<h1>Input scan result</h1>");
			html.append("<ul>");
			html.append("<li>");
			html.append("Number of errors : ").append(result.getNumberOfErrors());
			html.append("</li>");
			html.append("<li>");
			html.append("Scan time : ").append(result.getScanTime());
			html.append("</li>");
			html.append("<li>");
			html.append("Clean HTML : <br><textarea rows='10' cols='100'>").append(result.getCleanHTML()).append("</textarea>");
			html.append("</li>");
			html.append("</ul>");
			if (!result.getErrorMessages().isEmpty()) {
				html.append("<h1>List of detected errors</h1>");
				html.append("<ul>");
				for (Object o : result.getErrorMessages()) {
					html.append("<li>").append(o.toString()).append("</li>");
				}
				html.append("</ul>");
			}

		} catch (PolicyException pe) {
			html.append("<font color='red'>").append(pe.getMessage()).append("</font>");
		} catch (ScanException se) {
			html.append("<font color='red'>").append(se.getMessage()).append("</font>");
		}

		// Finalize response
		html.append("</body></html>");
		resp.getWriter().write(html.toString());
	}

}
