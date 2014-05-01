package com.googlecode.righettod.jee7json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import javax.json.stream.JsonParser;

import org.junit.Test;

/**
 * Test case containing exploration cases.<br>
 * <br>
 * Unfortunately, when this sample was wrote, object binding like GSON API seems to be not supported,<br>
 * It only support JSON processing (see stackoverflow.com ref below).<br>
 * <br>
 * Reader and Writer instances created using "Json.createXXX" close underlying input/output source used <br>
 * (I have checked this point at source code level).
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * @see "http://docs.oracle.com/javaee/7/tutorial/doc/jsonp002.htm"
 * @see "http://www.w3schools.com/json/json_syntax.asp"
 * @see "https://stackoverflow.com/questions/17345043/serialize-pojos-to-json-using-new-standard-javax-json"
 * 
 */
@SuppressWarnings({ "boxing", "static-method" })
public class ExplorationTestCase {

	/**
	 * Case in which we read a array from a local text file.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCase01() throws Exception {
		System.out.println("*** testCase01");
		try (JsonReader reader = Json.createReader(new FileReader("src/test/resources/case01.txt"))) {
			JsonArray arr = reader.readArray();
			System.out.println("Version 1 of items iteration");
			for (int i = 0; i < arr.size(); i++) {
				System.out.printf("Value[%s]=%s\n", i, arr.getString(i));
			}
			System.out.println("Version 2 of items iteration");
			for (JsonValue v : arr) {
				System.out.printf("Value=%s\n", v.toString());
			}
			// Close() on reader will be called by try-with-resources statement...
		}
	}

	/**
	 * Test case in which we read a object from a local text file and explore it <br>
	 * in a way in which we do not know is structure in advance...
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCase02() throws Exception {
		System.out.println("*** testCase02");
		try (JsonReader reader = Json.createReader(new FileReader("src/test/resources/case02.txt"))) {
			JsonObject obj = reader.readObject();
			navigateTree(obj, null);
			// Close() on reader will be called by try-with-resources statement...
		}
	}

	/**
	 * Test case in which we build a JSON object from scratch and save it to a local file.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCase03() throws Exception {
		System.out.println("*** testCase03");
		JsonArray arrLibraries = Json.createArrayBuilder().add("Lux City").add("Lux Concorde").build();
		JsonObject obj = Json.createObjectBuilder().add("bookName", "Lord of the Rings").add("priceInEuro", 25).add("libraries", arrLibraries).build();
		File f = new File("target/case03.txt");
		try (JsonWriter jsonWriter = Json.createWriter(new FileOutputStream(f, false))) {
			jsonWriter.writeObject(obj);
			System.out.printf("File saved to %s.\n", f.getAbsolutePath());
			// Close() on writer will be called by try-with-resources statement...
		}
	}

	/**
	 * Same case like Case 03 but using streaming API.<br>
	 * This example do not use Pretty Printing for JSON object structure.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCase04() throws Exception {
		System.out.println("*** testCase04");
		File f = new File("target/case04.txt");
		try (JsonGenerator gen = Json.createGenerator(new FileWriter(f))) {
			gen.writeStartObject();
			gen.write("bookName", "Lord of the Rings");
			gen.write("priceInEuro", 25);
			gen.writeStartArray("libraries");
			gen.write("Lux City");
			gen.write("Lux Concorde");
			gen.writeEnd();// End of Array
			gen.writeEnd();// End of Object
			System.out.printf("File saved to %s.\n", f.getAbsolutePath());
			// Close() on writer will be called by try-with-resources statement...
		}
	}

	/**
	 * Same case like Case 03 but using streaming API.<br>
	 * This example use Pretty Printing for JSON object structure.<br>
	 * To use Pretty Printing with Reader/Writer, instances must be created from a dedicated Factory in which Pretty Printing settings is enabled:<br>
	 * <code>
	 * Map<String, Object> config = new HashMap<>(1);
	 * config.put(JsonGenerator.PRETTY_PRINTING, true);
	 * JsonReaderFactory factoryReader = Json.createReaderFactory(config);
	 * JsonWriterFactory factoryWriter = Json.createWriterFactory(config);
	 * </code>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCase05() throws Exception {
		System.out.println("*** testCase05");
		File f = new File("target/case05.txt");
		Map<String, Object> config = new HashMap<>(1);
		config.put(JsonGenerator.PRETTY_PRINTING, true);
		JsonGeneratorFactory factory = Json.createGeneratorFactory(config);
		try (JsonGenerator gen = factory.createGenerator(new FileWriter(f))) {
			gen.writeStartObject();
			gen.write("bookName", "Lord of the Rings");
			gen.write("priceInEuro", 25);
			gen.writeStartArray("libraries");
			gen.write("Lux City");
			gen.write("Lux Concorde");
			gen.writeEnd();// End of Array
			gen.writeEnd();// End of Object
			System.out.printf("File saved to %s.\n", f.getAbsolutePath());
			// Close() on writer will be called by try-with-resources statement...
		}
	}

	/**
	 * Test case in which we read a object from a URL using Streaming API and explore it <br>
	 * in a way in which we do not know is structure in advance...
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCase06() throws Exception {
		System.out.println("*** testCase06");
		URL jsonLocation = new URL("http://www.reddit.com/.json");
		try (JsonParser parser = Json.createParser(jsonLocation.openStream())) {
			JsonParser.Event evt = null;
			while (parser.hasNext()) {
				evt = parser.next();
				switch (evt) {
				case START_ARRAY: {
					System.out.println("Beginning of array");
					break;
				}
				case END_ARRAY: {
					System.out.println("End of array");
					break;
				}
				case START_OBJECT: {
					System.out.println("Beginning of object");
					break;
				}
				case END_OBJECT: {
					System.out.println("End of object");
					break;
				}
				case VALUE_FALSE: {
					System.out.println("Value FALSE detected");
					break;
				}
				case VALUE_NULL: {
					System.out.println("Value NULL detected");
					break;
				}
				case VALUE_TRUE: {
					System.out.println("Value TRUE detected");
					break;
				}
				case KEY_NAME:
					System.out.printf("New KEY detected with name '%s'\n", parser.getString());
					break;
				case VALUE_STRING: {
					System.out.printf("New VALUE (string) detected '%s'\n", parser.getString());
					break;
				}
				case VALUE_NUMBER:
					System.out.printf("New VALUE (number) detected '%s'\n", parser.getInt());
					break;

				default: {
					System.out.printf("Unknow event type detected '%s'\n", evt.name());
					break;
				}
				}
			}
			// Close() on parser will be called by try-with-resources statement...
		}

	}

	/**
	 * Object navigation example taken from JEE7 Oracle tutorial.
	 * 
	 * @param tree Json value to use exploratin starting point.
	 * @param key Json value identifier.
	 * @see "http://docs.oracle.com/javaee/7/tutorial/doc/jsonp003.htm"
	 */
	private void navigateTree(JsonValue tree, String key) {
		if (key != null)
			System.out.print("Key " + key + ": ");
		switch (tree.getValueType()) {
		case OBJECT:
			System.out.println("OBJECT");
			JsonObject object = (JsonObject) tree;
			for (String name : object.keySet())
				navigateTree(object.get(name), name);
			break;
		case ARRAY:
			System.out.println("ARRAY");
			JsonArray array = (JsonArray) tree;
			for (JsonValue val : array)
				navigateTree(val, null);
			break;
		case STRING:
			JsonString st = (JsonString) tree;
			System.out.println("STRING " + st.getString());
			break;
		case NUMBER:
			JsonNumber num = (JsonNumber) tree;
			System.out.println("NUMBER " + num.toString());
			break;
		case TRUE:
		case FALSE:
		case NULL:
			System.out.println(tree.getValueType().toString());
			break;
		default: {
			System.out.println("UNKNOW");
			break;
		}
		}
	}

}
