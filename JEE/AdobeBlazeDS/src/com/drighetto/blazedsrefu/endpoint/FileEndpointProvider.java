package com.drighetto.blazedsrefu.endpoint;

import java.io.File;
import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import flex.messaging.util.StringUtils;

/**
 * Servlet used to obtains a URL endpoint to download a file
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class FileEndpointProvider extends HttpServlet implements Servlet {

	/** Serial UID */
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Get the path of the file to download from a HTTP defined parameter
		if (!StringUtils.isEmpty(request.getParameter("FILE_PATH"))) {
			File path = new File(request.getParameter("FILE_PATH"));
			// Check file existence
			if (!path.exists()) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"A HTTP parameter named 'FILE_PATH' must be provided with a valid local file path !");
			} else {
				response.getOutputStream().write(FileUtils.readFileToByteArray(path));
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		} else {
			// If the parameter is not provided we send a error response
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"A HTTP parameter named 'FILE_PATH' must be provided with the full local file path !");
		}
	}

}