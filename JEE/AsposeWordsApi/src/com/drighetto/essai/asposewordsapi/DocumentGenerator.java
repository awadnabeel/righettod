package com.drighetto.essai.asposewordsapi;

import com.aspose.words.BreakType;
import com.aspose.words.Cell;
import com.aspose.words.CellMerge;
import com.aspose.words.CellVerticalAlignment;
import com.aspose.words.Comment;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.FootnoteType;
import com.aspose.words.HeaderFooterType;
import com.aspose.words.Paragraph;
import com.aspose.words.Run;
import com.aspose.words.SaveFormat;
import com.aspose.words.TextOrientation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Simple document generator
 * 
 * @author Dominique RIGHETTO
 * 
 */
public class DocumentGenerator extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	@SuppressWarnings("boxing")
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		try {
			// /* Info msg */
			// System.out.printf("Process request from : %s\n", request
			// .getRemoteAddr());
			/* Create a simple document */
			long start = System.currentTimeMillis();
			Document wordDoc = new Document();
			DocumentBuilder wordDocBuilder = new DocumentBuilder(wordDoc);
			// Add Header / Footer
			wordDocBuilder.getPageSetup().setDifferentFirstPageHeaderFooter(
					false);
			wordDocBuilder.getPageSetup().setOddAndEvenPagesHeaderFooter(false);
			wordDocBuilder.getPageSetup().setHeaderDistance(20);
			wordDocBuilder.getPageSetup().setFooterDistance(20);
			wordDocBuilder.moveToHeaderFooter(HeaderFooterType.HEADER_PRIMARY);
			wordDocBuilder.writeln("My HEADER");
			wordDocBuilder.moveToHeaderFooter(HeaderFooterType.FOOTER_PRIMARY);
			wordDocBuilder.writeln("My FOOTER");
			wordDocBuilder.moveToDocumentEnd();
			// Set font properties
			wordDocBuilder.setBold(true);
			wordDocBuilder.setItalic(true);
			// Add text
			wordDocBuilder.writeln("My Text");
			wordDocBuilder.writeln();
			// Add end footer note and foot note
			wordDocBuilder.insertFootnote(FootnoteType.ENDNOTE, "My End Note");
			wordDocBuilder
					.insertFootnote(FootnoteType.FOOTNOTE, "My Foot Note");
			wordDocBuilder.writeln();
			// Add hyperlink
			wordDocBuilder.insertHyperlink("LINK", "http://www.google.lu",
					false);
			wordDocBuilder.writeln();
			// Add a table
			wordDocBuilder.startTable();
			// --Row 1 with 2 cell
			Cell cell = wordDocBuilder.insertCell();
			cell.getCellFormat().setVerticalAlignment(
					CellVerticalAlignment.CENTER);
			wordDocBuilder.writeln("Row 1 Cell 1 Text");
			cell = wordDocBuilder.insertCell();
			cell.getCellFormat().setVerticalAlignment(
					CellVerticalAlignment.CENTER);
			wordDocBuilder.writeln("Row 1 Cell 2 Text");
			wordDocBuilder.endRow();
			// --Row 2 with 2 cell
			cell = wordDocBuilder.insertCell();
			cell.getCellFormat().setVerticalAlignment(
					CellVerticalAlignment.BOTTOM);
			wordDocBuilder.writeln("Row 2 Cell 1 Text");
			cell = wordDocBuilder.insertCell();
			cell.getCellFormat()
					.setVerticalAlignment(CellVerticalAlignment.TOP);
			wordDocBuilder.writeln("Row 2 Cell 2 Text");
			wordDocBuilder.endRow();
			// --Row 3 with 3 cell merged
			cell = wordDocBuilder.insertCell();
			cell.getCellFormat().setVerticalAlignment(
					CellVerticalAlignment.CENTER);
			cell.getCellFormat().setOrientation(TextOrientation.HORIZONTAL);
			cell.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
			wordDocBuilder.writeln("Row 3 Cell 3 Text");
			cell = wordDocBuilder.insertCell();
			cell.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
			wordDocBuilder.endRow();
			wordDocBuilder.endTable();
			// Add Comment
			Comment comment = new Comment(wordDoc);
			comment.setAuthor("D.Righetto");
			comment.getParagraphs().add(new Paragraph(wordDoc));
			comment.getFirstParagraph().getRuns().add(
					new Run(wordDoc, "My Comment text !!!"));
			wordDocBuilder.getCurrentParagraph().appendChild(comment);
			// Add break
			wordDocBuilder.insertBreak(BreakType.PAGE_BREAK);
			// Add image
			String imgUrl = "http://" + request.getServerName() + ":"
					+ request.getServerPort() + request.getContextPath()
					+ "/img/AsposeWords.gif";
			// System.out.printf("Insert image from : %s\n", imgUrl);
			wordDocBuilder.insertImage(imgUrl);
			System.out.printf("Document generated in %s ms\n", (System
					.currentTimeMillis() - start));

			/* Send document to client */
			response.setContentType("application/vnd.ms-word");
			response.addHeader("Content-Disposition",
					"inline;filename=myWordDocument.doc");
			wordDoc.save(response.getOutputStream(), SaveFormat.DOC);
			response.flushBuffer();
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		doGet(request, response);
	}

}