package com.drighetto.fop.essai.servlet;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

import org.apache.commons.io.output.ByteArrayOutputStream;
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
 * Servlet generating a PDF stream with JS code included
 * 
 * @author Dominique RIGHETTO<br>
 *         18 sept. 07<br>
 */
public class PDFGeneratorWithJS extends HttpServlet implements Servlet {
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
		ByteArrayOutputStream fopout = new ByteArrayOutputStream();
		long start = System.currentTimeMillis();

		try {
			// Generate a PDF stream to the ByteArrayOutputStream using FOP
			System.out.printf("-> PDFGeneratorWithJS:Request from [%s]\n",
					request.getRemoteAddr());
			response.setContentType("application/pdf");
			Fop fop = this.fopFactory.newFop(MimeConstants.MIME_PDF, fopout);
			Transformer transformer = this.tFactory.newTransformer();
			transformer.setURIResolver(this.uriResolver);
			Source src = this.uriResolver.resolve(
					"servlet-context:/xsl-fo/simple.fo", null);
			Result res = new SAXResult(fop.getDefaultHandler());
			transformer.transform(src, res);

			// Add Javascript code using iText to read/update PDF stream
			// --Create a reader and a writer to tranfer/update content
			PdfReader reader = new PdfReader(fopout.toByteArray());
			Document document = new Document(reader.getPageSizeWithRotation(1));
			PdfWriter writer = PdfWriter.getInstance(document, response
					.getOutputStream());
			// --Tranfer content
			int n = reader.getNumberOfPages();
			document.open();
			PdfContentByte cb = writer.getDirectContent();
			PdfImportedPage page;
			int rotation;
			int i = 0;
			while (i < n) {
				i++;
				document.setPageSize(reader.getPageSizeWithRotation(i));
				document.newPage();
				page = writer.getImportedPage(reader, i);
				rotation = reader.getPageRotation(i);
				if (rotation == 90 || rotation == 270) {
					cb.addTemplate(page, 0, -1f, 1f, 0, 0, reader
							.getPageSizeWithRotation(i).getHeight());
				} else {
					cb.addTemplate(page, 1f, 0, 0, 1f, 0, 0);
				}
				System.out.printf("Processed page %s\n", i);
			}
			// --Add JS code
			writer
					.addJavaScript(
							"this.print({bUI: false, bSilent: true, bShrinkToFit: true});",
							false);
			// --Close document and stream
			document.close();
			writer.close();
			reader.close();
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