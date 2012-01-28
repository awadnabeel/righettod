package com.drighetto.xstream;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

import de.schlichtherle.io.File;
import de.schlichtherle.util.zip.ZipFile;

/**
 * Samples using XStream API to serialize/deserialize object :<br>
 * <ul>
 * <li>From and to XML string</li>
 * <li>From and to object stream</li>
 * </ul>
 * 
 * WebSite : http://xstream.codehaus.org
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
public class XStreamSample {

	/**
	 * XStream processor
	 */
	private static final XStream X_STREAM = new XStream();

	/**
	 * SAX XML Parser
	 */
	private static final SAXReader SAX_READER = new SAXReader();

	/**
	 * Entry point
	 * 
	 * @param args
	 *            Command line argument
	 */
	@SuppressWarnings("boxing")
	public static void main(String[] args) {
		try {

			/* SAMPLE WITH OBJECT STREAM */
			for (int i = 0; i < 10; i++) {
				long freeMemoryBef = Runtime.getRuntime().freeMemory();
				sampleObjectAction();
				long freeMemoryAft = Runtime.getRuntime().freeMemory();
				System.out.println("####JVM State#################");
				System.out.printf("freeMemoryBefore : %s\n", freeMemoryBef);
				System.out.printf("freeMemoryAfter : %s\n", freeMemoryAft);
				System.out.printf("Diff : %s\n",
						(freeMemoryBef - freeMemoryAft));
				System.out.println("##############################");
			}
			/* SAMPLE WITH XML STRING STREAM */
			// for (int i = 0; i < 10; i++) {
			// long freeMemoryBef = Runtime.getRuntime().freeMemory();
			// sampleXmlAction();
			// long freeMemoryAft = Runtime.getRuntime().freeMemory();
			// System.out.println("####JVM State#################");
			// System.out.printf("freeMemoryBefore : %s\n", freeMemoryBef);
			// System.out.printf("freeMemoryAfter : %s\n", freeMemoryAft);
			// System.out.printf("Diff : %s\n",
			// (freeMemoryBef - freeMemoryAft));
			// System.out.println("##############################");
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method that show the serialization/deserialzation of a object that is a
	 * zip archive, this one use Object I/O stream through XML String
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("boxing")
	static void sampleObjectAction() throws Exception {
		System.out
				.println("****************sampleObjectAction()************************");
		long gStart = System.currentTimeMillis();
		// Serialize object
		long start = System.currentTimeMillis();
		ZipFile zFileBef = new ZipFile(new File("samplefiles/sample00.docx"));
		StringWriter stringWriter = new StringWriter();
		ObjectOutputStream out = X_STREAM
				.createObjectOutputStream(stringWriter);
		out.writeObject(zFileBef);
		out.close();
		String data = stringWriter.toString();
		System.out.printf("Serialization delay       : %s\n", (System
				.currentTimeMillis() - start));
		// Deserialize object
		start = System.currentTimeMillis();
		StringReader stringReader = new StringReader(data);
		ObjectInputStream in = X_STREAM.createObjectInputStream(stringReader);
		ZipFile zFileAft = (ZipFile) in.readObject();
		System.out.printf("Deserialization delay     : %s\n", (System
				.currentTimeMillis() - start));
		// Check objects validity
		// --Original stream
		start = System.currentTimeMillis();
		InputStream inStreamBef = zFileBef.getInputStream(zFileBef
				.getEntry("word/document.xml"));
		Document coreDocumentBef = SAX_READER.read(inStreamBef);
		System.out.printf("Create XML document delay : %s\n", (System
				.currentTimeMillis() - start));
		// --Deserialized stream
		start = System.currentTimeMillis();
		InputStream inStreamAft = zFileAft.getInputStream(zFileAft
				.getEntry("word/document.xml"));
		Document coreDocumentAft = SAX_READER.read(inStreamAft);
		System.out.printf("Create XML document delay : %s\n", (System
				.currentTimeMillis() - start));
		System.out.printf("Global delay inter        : %s\n", (System
				.currentTimeMillis() - gStart));
		// --Check
		start = System.currentTimeMillis();
		if (!coreDocumentBef.asXML().equals(coreDocumentAft.asXML())) {
			throw new Exception("ERROR");
		}
		System.out.printf("Check delay               : %s\n", (System
				.currentTimeMillis() - start));
		System.out.printf("Global delay              : %s\n", (System
				.currentTimeMillis() - gStart));
		System.out.println("Check OK");
		System.out
				.println("************************************************************");
	}

	/**
	 * Method that show the serialization and deserialization of a simple pojo
	 * object to and from XML String
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("boxing")
	static void sampleXmlAction() throws Exception {
		System.out
				.println("****************sampleXmlAction()************************");
		long gStart = System.currentTimeMillis();
		// Create a pojo
		SimplePojo sp = new SimplePojo();
		sp.setMessage("Hello");
		sp.setSender("Me");
		// Serialize to XML
		long start = System.currentTimeMillis();
		String xml = X_STREAM.toXML(sp);
		System.out.printf("XML Serialization String  : \n%s\n", xml);
		System.out.printf("Serialization delay       : %s\n", (System
				.currentTimeMillis() - start));
		// Deserialize from XML
		start = System.currentTimeMillis();
		SimplePojo sp2 = (SimplePojo) X_STREAM.fromXML(xml);
		System.out.printf("Deserialization delay     : %s\n", (System
				.currentTimeMillis() - start));
		// Check
		start = System.currentTimeMillis();
		if (!sp.equals(sp2)) {
			throw new Exception("ERROR");
		}
		System.out.printf("Check delay               : %s\n", (System
				.currentTimeMillis() - start));
		System.out.printf("Global delay              : %s\n", (System
				.currentTimeMillis() - gStart));
		System.out.println("Check OK");
		System.out
				.println("************************************************************");
	}

}
