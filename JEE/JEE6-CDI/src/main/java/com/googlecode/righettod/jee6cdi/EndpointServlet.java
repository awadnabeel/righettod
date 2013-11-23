package com.googlecode.righettod.jee6cdi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.DecoderException;

import com.googlecode.righettod.jee6cdi.bean.Encoder;
import com.googlecode.righettod.jee6cdi.qualifier.Base64Format;
import com.googlecode.righettod.jee6cdi.qualifier.HexadecimalFormat;

/**
 * Simple servlet in order to show injection by CDI container.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("serial")
@WebServlet(name = "EndpointServlet", urlPatterns = { "/test" })
public class EndpointServlet extends HttpServlet {

	/** Injection of an Encoder requesting Default Encoder implementation */
	private @Inject
	Encoder encoderDefaultImpl;

	/** Injection of an Encoder requesting Base64 Encoder implementation */
	private @Inject
	@Base64Format
	Encoder encoderB64Impl;

	/** Injection of an Encoder requesting Hexadecimal Encoder implementation */
	private @Inject
	@HexadecimalFormat
	Encoder encoderHexImpl;

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tmp = null;
		String str = req.getParameter("str");
		if (str == null) {
			str = "Hello World !";
		}
		try (PrintWriter w = resp.getWriter()) {
			resp.setContentType("text/plain");
			//
			w.println("----Default Encoder implementation----");
			tmp = this.encoderDefaultImpl.encode(str);
			w.printf("Encoded String '%s'\n", tmp);
			w.printf("Decoded String '%s'\n", this.encoderDefaultImpl.decode(tmp));
			//
			w.println("----Base64 Encoder implementation----");
			tmp = this.encoderB64Impl.encode(str);
			w.printf("Encoded String '%s'\n", tmp);
			w.printf("Decoded String '%s'\n", this.encoderB64Impl.decode(tmp));
			//
			w.println("----Hexadecimal Encoder implementation----");
			tmp = this.encoderHexImpl.encode(str);
			w.printf("Encoded String '%s'\n", tmp);
			w.printf("Decoded String '%s'\n", this.encoderHexImpl.decode(tmp));
		}
		catch (DecoderException e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			e.printStackTrace();
		}
	}

}
