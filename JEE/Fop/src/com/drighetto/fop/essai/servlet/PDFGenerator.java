package com.drighetto.fop.essai.servlet;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.fop.servlet.ServletContextURIResolver;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXResult;

/**
 * Servlet generating a PDF stream based on XSL:FO sheet
 * 
 * @author Dominique RIGHETTO<br>
 *         18 sept. 07<br>
 */
public class PDFGenerator extends HttpServlet implements Servlet {
	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 1L;

	/** FOP Factory */
	private FopFactory fopFactory = FopFactory.newInstance();

	/** XML Transformer */
	private TransformerFactory tFactory = TransformerFactory.newInstance();

	/** URIResolver for use by this servlet */
	private URIResolver uriResolver = null;

	/**
	 * @see javax.servlet.GenericServlet#init()
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void init() {
		// Get FOP URI resolver for web resource access
		this.uriResolver = new ServletContextURIResolver(getServletContext());
		// Set URI resolver for the FOP Factory and the XML transformer
		this.fopFactory.setURIResolver(this.uriResolver);
		this.tFactory.setURIResolver(this.uriResolver);
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 * 
	 * {@inheritDoc}
	 */
	@SuppressWarnings("boxing")
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		long start = System.currentTimeMillis();
		try {
			// Generate a PDF stream
			System.out.printf("-> PDFGenerator:Request from [%s]\n", request
					.getRemoteAddr());
			response.setContentType("application/pdf");
			Fop fop = this.fopFactory.newFop(MimeConstants.MIME_PDF, response
					.getOutputStream());
			Transformer transformer = this.tFactory.newTransformer();
			transformer.setURIResolver(this.uriResolver);
			Source src = this.uriResolver.resolve(
					"servlet-context:/xsl-fo/simple.fo", null);
			Result res = new SAXResult(fop.getDefaultHandler());
			transformer.transform(src, res);
			System.out.printf("Generated in %s ms\n", (System
					.currentTimeMillis() - start));
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 * 
	 * {@inheritDoc}
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		this.doGet(request, response);
	}
}