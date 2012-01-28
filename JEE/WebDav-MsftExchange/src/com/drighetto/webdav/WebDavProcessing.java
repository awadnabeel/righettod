package com.drighetto.webdav;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.httpclient.HttpURL;
import org.apache.webdav.lib.PropertyName;
import org.apache.webdav.lib.WebdavResource;

/**
 * Sample of Exchange and Webdav interaction
 * 
 * @author Dominique Righetto<br>
 *         02-mai-07
 * 
 */
public class WebDavProcessing {

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

	DateFormat idDateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");

	/**
	 * Sample creating Draft mail in Exchange repository
	 */
	public void addDraftMail() {
		HttpURL url = null;
		WebdavResource resource = null;

		try {
			/* Set XML Namespace collection */
			Hashtable<String, String> namespace = new Hashtable<String, String>();
			namespace.put("a", "DAV:");
			namespace
					.put("b", "urn:uuid:c2f41010-65b3-11d1-a29f-00aa00c14882/");
			namespace.put("g", "http://schemas.microsoft.com/mapi/");
			namespace.put("e", "urn:schemas:httpmail:");
			namespace.put("d", "urn:schemas:mailheader:");
			namespace.put("c", "xml:");
			namespace.put("f", "http://schemas.microsoft.com/mapi/proptag/");
			namespace.put("h", "http://schemas.microsoft.com/exchange/");
			namespace.put("i", "urn:schemas-microsoft-com:office:office");
			namespace.put("k", "http://schemas.microsoft.com/repl/");
			namespace.put("j", "urn:schemas:calendar:");
			namespace.put("l", "urn:schemas-microsoft-com:exch-data:");

			/* Connect to Exchange webdav repository */
			url = new HttpURL(Constant.DAV_URL);
			url.setUserinfo(Constant.APPLICATION_USER_ACCOUNT_NAME,
					Constant.APPLICATION_USER_PASSWORD);
			resource = new WebdavResource(url);
			resource.setDebug(Constant.DEBUG_LEVEL);

			/* Create draft mail (XML request) */
			Hashtable<Object, String> props = new Hashtable<Object, String>();
			// <a:contentclass>urn:content-classes:message</a:contentclass>
			props.put(new PropertyName(namespace.get("a"), "contentclass"),
					"urn:content-classes:message");
			// <h:outlookmessageclass>IPM.Note</h:outlookmessageclass>
			props.put(new PropertyName(namespace.get("h"),
					"outlookmessageclass"), "IPM.Note");
			// <d:to>foo@foobar.com</d:to>
			props.put(new PropertyName(namespace.get("d"), "to"),
					"dominique.righetto@laposte.net");
			// <d:cc>bar@foobar.com</d:cc>
			props.put(new PropertyName(namespace.get("d"), "cc"),
					"neotux@laposte.net");
			// <d:importance>High</d:importance>
			props.put(new PropertyName(namespace.get("d"), "importance"),
					"High");
			// <d:bcc>bob@aol.com</d:bcc>
			props
					.put(new PropertyName(namespace.get("d"), "bcc"),
							"dri@flc.be");
			// <g:subject>$subject</g:subject>
			props.put(new PropertyName(namespace.get("g"), "subject"), "Test");
			// <e:htmldescription>This is spam. Please delete this
			// email.</e:htmldescription>
			props.put(new PropertyName(namespace.get("e"), "htmldescription"),
					"This is spam. Please delete this email.");

			/* Send webdav request and read response... */
			if (resource.proppatchMethod(Constant.DAV_URL + "Drafts/Test_"
					+ idDateFormat.format(new Date()) + ".EML", props, true)) {
				System.out.printf(
						"Draft mail creation OK - Status code returned: %s",
						resource.getStatusMessage());
			} else {
				System.out.printf("Request failed, Status code returned: %s",
						resource.getStatusMessage());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				// Release connection...
				if (resource != null)
					resource.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	/**
	 * Sample creating Task in Exchange repository
	 */
	public void addTask() {
		HttpURL url = null;
		WebdavResource resource = null;

		try {
			/* Set XML Namespace collection */
			Hashtable<String, String> namespace = new Hashtable<String, String>();
			namespace.put("a", "DAV:");
			namespace
					.put("b", "urn:uuid:c2f41010-65b3-11d1-a29f-00aa00c14882/");
			namespace.put("g", "http://schemas.microsoft.com/mapi/");
			namespace.put("e", "urn:schemas:httpmail:");
			namespace.put("d", "urn:schemas:mailheader:");
			namespace.put("c", "xml:");
			namespace.put("f", "http://schemas.microsoft.com/mapi/proptag/");
			namespace.put("h", "http://schemas.microsoft.com/exchange/");
			namespace.put("i", "urn:schemas-microsoft-com:office:office");
			namespace.put("k", "http://schemas.microsoft.com/repl/");
			namespace.put("j", "urn:schemas:calendar:");
			namespace.put("l", "urn:schemas-microsoft-com:exch-data:");
			namespace.put("m", "http://schemas.microsoft.com/exchange/tasks/");

			/* Connect to Exchange webdav repository */
			url = new HttpURL(Constant.DAV_URL);
			url.setUserinfo(Constant.APPLICATION_USER_ACCOUNT_NAME,
					Constant.APPLICATION_USER_PASSWORD);
			resource = new WebdavResource(url);
			resource.setDebug(Constant.DEBUG_LEVEL);

			/* Create task (XML request) */
			Hashtable<Object, String> props = new Hashtable<Object, String>();
			props.put(new PropertyName(namespace.get("a"), "contentclass"),
					"urn:content-classes:task");
			props.put(new PropertyName(namespace.get("h"),
					"outlookmessageclass"), "IPM.Task");
			props.put(new PropertyName(namespace.get("d"), "subject"),
					"TestTask");
			props.put(new PropertyName(namespace.get("d"), "importance"),
					"High");
			props.put(new PropertyName(namespace.get("e"), "textdescription"),
					"This is spam Task. Please delete this task.");
			// Priority : 0 = Low ; 1 = Normal ; 2 = High
			props.put(new PropertyName(namespace.get("h"), "x-priority-long"),
					"1");			
			props.put(new PropertyName(namespace.get("m"), "dtdue"), dateFormat
					.format(new Date()));
			props.put(new PropertyName(namespace.get("m"), "dtstart"),
					dateFormat.format(new Date()));
			props.put(new PropertyName(namespace.get("m"), "percentcomplete"),
					"0.5");
			// Status : 0 = Not Started ; 1 = In Progress ; 2 = Completed ;
			// 3 = Waiting on someone else ; 4 = Deferred
			props.put(new PropertyName(namespace.get("m"), "status"), "2");
			props.put(new PropertyName(namespace.get("g"), "reminderset"), "1");
			props.put(new PropertyName(namespace.get("g"), "remindertime"),
					dateFormat.format(new Date()));

			/* Send webdav request and read response... */
			if (resource.proppatchMethod(Constant.DAV_URL + "Tasks/Test_"
					+ idDateFormat.format(new Date()) + ".EML", props, true)) {
				System.out.printf(
						"Task creation OK - Status code returned: %s", resource
								.getStatusMessage());
			} else {
				System.out.printf("Request failed, Status code returned: %s",
						resource.getStatusMessage());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				// Release connection...
				if (resource != null)
					resource.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}
