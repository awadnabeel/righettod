package com.drighetto.essai.asposewordsapi;

import com.aspose.words.Document;
import com.aspose.words.FormField;
import com.aspose.words.FormFields;
import com.aspose.words.SaveFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Simple document modifier
 * 
 * @author Dominique RIGHETTO
 * 
 */
public class DocumentModifier extends javax.servlet.http.HttpServlet implements
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
			String dotUrl = "http://" + request.getServerName() + ":"
					+ request.getServerPort() + request.getContextPath()
					+ "/dot/MODELE_VIDE_FR.dot";
			dotUrl = "D:\\TMP\\ACC_RECEP_REFERE_IT.dot";
			long start = System.currentTimeMillis();
			Document wordDoc = new Document(dotUrl);

			// /* Replace bookmark value */
			// Get document bookmarks collection
			// Bookmarks bookmarks = wordDoc.getRange().getBookmarks();
			// for (int i = 0; i < bookmarks.getCount(); i++) {
			// Bookmark bookmark = bookmarks.get(i);
			// String value = "VALUE_" + Integer.toString(i);
			// System.out.printf(
			// "Processing bookmark['%s'] : ['%s'] to ['%s']\n",
			// bookmark.getName(), bookmark.getText(), value);
			// bookmark.setText(value);
			// }
			// /* Replace text directly */
			// int count = wordDoc.getRange().replace("DATE_DEP_AFF_FAX",
			// "DOMINIQUE", true, true);
			// System.out.printf("%s items replaced !\n", count);
			/* Replace text fields */
			FormFields formFields = wordDoc.getRange().getFormFields();
			for (int i = 0; i < formFields.getCount(); i++) {
				FormField formField = formFields.get(i);
				String value = "VALUE_" + Integer.toString(i);
				System.out.printf("Processing FormFiled['%s'] to ['%s']\n",
						formField.getName(), value);
				int count = formField.getDocument().getRange().replace(
						formField.getName(), value, true, true);
				System.out.printf("%s items replaced !\n", count);
			}

			System.out.printf("Document modified in %s ms\n", (System
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