package com.googlecode.righettod.msgpack;

import static org.msgpack.template.Templates.TString;
import static org.msgpack.template.Templates.tList;
import static org.msgpack.template.Templates.tMap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;
import org.msgpack.template.Template;
import org.msgpack.unpacker.Unpacker;

/**
 * Test cases to explore MsgPack API.
 * 
 * @see "http://wiki.msgpack.org/display/MSGPACK/QuickStart+for+Java"
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("static-method")
public class ExplorationTest {

	/**
	 * Test case serializing short String.<br>
	 * <b>Way to use Packer/Unpacker for non sequential use (one shot). <br>
	 * MessagePack.write(Object) and read(byte[]) method invocations create Packer and Unpacker objects every times.</b>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUsingShortStringOneShotMode() throws Exception {
		long startTime = 0;
		String dataSrc = RandomStringUtils.randomAlphanumeric(50);
		MessagePack packer = new MessagePack();
		// Serialization
		startTime = System.currentTimeMillis();
		byte[] serializedData = packer.write(dataSrc);
		printProcessingTime("testUsingShortStringOneShotMode::Serialization ", startTime);
		// Deserialization
		startTime = System.currentTimeMillis();
		String data = packer.read(serializedData, dataSrc.getClass());
		printProcessingTime("testUsingShortStringOneShotMode::Deserialization ", startTime);
		// Check
		Assert.assertEquals(dataSrc, data);
	}

	/**
	 * Test case serializing long String.<br>
	 * <b>Way to use Packer/Unpacker for non sequential use (one shot). <br>
	 * MessagePack.write(Object) and read(byte[]) method invocations create Packer and Unpacker objects every times.</b>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUsingLongStringOneShotMode() throws Exception {
		long startTime = 0;
		String dataSrc = RandomStringUtils.randomAlphanumeric(1000000);
		MessagePack packer = new MessagePack();
		// Serialization
		startTime = System.currentTimeMillis();
		byte[] serializedData = packer.write(dataSrc);
		printProcessingTime("testUsingLongStringOneShotMode::Serialization ", startTime);
		// Deserialization
		startTime = System.currentTimeMillis();
		String data = packer.read(serializedData, dataSrc.getClass());
		printProcessingTime("testUsingLongStringOneShotMode::Deserialization ", startTime);
		// Check
		Assert.assertEquals(dataSrc, data);
	}

	/**
	 * Test case serializing short String.<br>
	 * <b>Optimized use of Packer/Unpacker for sequential use.<br>
	 * Call createPacker(OutputStream) and createUnpacker(InputStream).</b>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUsingShortStringOptimizedForSequentialUse() throws Exception {
		long startTime = 0;
		String dataSrc = RandomStringUtils.randomAlphanumeric(50);
		MessagePack msgpack = new MessagePack();
		// Serialization
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		startTime = System.currentTimeMillis();
		Packer packer = msgpack.createPacker(baos);
		packer.write(dataSrc.getBytes());
		packer.close();
		byte[] serializedData = baos.toByteArray();
		printProcessingTime("testUsingShortStringOptimizedForSequentialUse::Serialization ", startTime);
		// Deserialization
		ByteArrayInputStream bais = new ByteArrayInputStream(serializedData);
		startTime = System.currentTimeMillis();
		Unpacker unpacker = msgpack.createUnpacker(bais);
		String data = unpacker.readString();
		unpacker.close();
		printProcessingTime("testUsingShortStringOptimizedForSequentialUse::Deserialization ", startTime);
		// Check
		Assert.assertEquals(dataSrc, data);
	}

	/**
	 * Test case serializing long String.<br>
	 * <b>Optimized use of Packer/Unpacker for sequential use.<br>
	 * Call createPacker(OutputStream) and createUnpacker(InputStream).</b>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUsingLongStringOptimizedForSequentialUse() throws Exception {
		long startTime = 0;
		String dataSrc = RandomStringUtils.randomAlphanumeric(1000000);
		MessagePack msgpack = new MessagePack();
		// Serialization
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		startTime = System.currentTimeMillis();
		Packer packer = msgpack.createPacker(baos);
		packer.write(dataSrc.getBytes());
		packer.close();
		byte[] serializedData = baos.toByteArray();
		printProcessingTime("testUsingLongStringOptimizedForSequentialUse::Serialization ", startTime);
		// Deserialization
		ByteArrayInputStream bais = new ByteArrayInputStream(serializedData);
		startTime = System.currentTimeMillis();
		Unpacker unpacker = msgpack.createUnpacker(bais);
		String data = unpacker.readString();
		unpacker.close();
		printProcessingTime("testUsingLongStringOptimizedForSequentialUse::Deserialization ", startTime);
		// Check
		Assert.assertEquals(dataSrc, data);
	}

	/**
	 * Test case for compare size on disk.<br>
	 * Serialized data take more space on disk ;o)
	 * 
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("boxing")
	@Test
	public void testStorageDataSizeOnDisk() throws Exception {
		String dataSrc = RandomStringUtils.randomAlphanumeric(1000000);
		File fileWithDataNotSerialized = new File("data-notserialzed.txt");
		File fileWithDataSerialized = new File("data-serialzed.txt");
		MessagePack msgpack = new MessagePack();
		// Store not serialized data
		FileUtils.deleteQuietly(fileWithDataNotSerialized);
		FileUtils.writeStringToFile(fileWithDataNotSerialized, dataSrc);
		// Serialize and store serialized data
		FileUtils.deleteQuietly(fileWithDataSerialized);
		byte[] serData = msgpack.write(dataSrc);
		FileUtils.writeByteArrayToFile(fileWithDataSerialized, serData);
		// Display files size
		long u = FileUtils.sizeOf(fileWithDataNotSerialized);
		long c = FileUtils.sizeOf(fileWithDataSerialized);
		System.out.printf("testStorageDataSizeOnDisk::NotSerialized %s bytes\n", u);
		System.out.printf("testStorageDataSizeOnDisk::Serialized    %s bytes\n", c);
		System.out.printf("testStorageDataSizeOnDisk::Delta         %s bytes\n", (u - c));
		// Check
		String data = msgpack.read(FileUtils.readFileToByteArray(fileWithDataSerialized), dataSrc.getClass());
		Assert.assertEquals(dataSrc, data);
	}

	/**
	 * Test to explore collection serialization.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCollectionSerialization() throws Exception {
		MessagePack msgpack = new MessagePack();

		/* Create templates for serializing/deserializing List and Map objects */
		Template<List<String>> listTpl = tList(TString);
		Template<Map<String, String>> mapTpl = tMap(TString, TString);

		/* Serialization */
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Packer packer = msgpack.createPacker(baos);
		// Serialize List object
		List<String> list = new ArrayList<String>();
		list.add("msgpack");
		list.add("for");
		list.add("java");
		packer.write(list);
		// Serialize Map object
		Map<String, String> map = new HashMap<String, String>();
		map.put("sadayuki", "furuhashi");
		map.put("muga", "nishizawa");
		packer.write(map);
		packer.close();

		/* Storage on disk */
		File fileWithDataSerialized = new File("data-serialzed.txt");
		FileUtils.deleteQuietly(fileWithDataSerialized);
		FileUtils.writeByteArrayToFile(fileWithDataSerialized, baos.toByteArray());

		/* Deserialization */
		byte[] serData = FileUtils.readFileToByteArray(fileWithDataSerialized);
		ByteArrayInputStream bais = new ByteArrayInputStream(serData);
		Unpacker unpacker = msgpack.createUnpacker(bais);
		// Deserialize List object
		List<String> dstList = unpacker.read(listTpl);
		// Deserialize Map object
		Map<String, String> dstMap = unpacker.read(mapTpl);
		unpacker.close();

		/* Check */
		Assert.assertTrue(dstList.size() == list.size());
		for (int i = 0; i < list.size(); i++) {
			Assert.assertEquals(list.get(i), dstList.get(i));
		}
		Assert.assertTrue(dstMap.size() == map.size());
		for (String key : map.keySet()) {
			Assert.assertTrue(dstMap.containsKey(key));
			Assert.assertEquals(map.get(key), dstMap.get(key));
		}
	}

	/**
	 * Test to explore POJO serialization.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPojoSerialization() throws Exception {
		MessagePack msgpack = new MessagePack();

		/* Create Pojos */
		SimplePojo p1 = new SimplePojo();
		p1.member01 = "Dom";
		p1.member02 = "Dodo";
		SimplePojo p2 = new SimplePojo();
		p2.member01 = "Mimi";
		p2.member02 = "Domimi";

		/* Serialization */
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Packer packer = msgpack.createPacker(baos);
		// Serialize objects
		packer.write(p1);
		packer.write(p2);
		packer.close();

		/* Storage on disk */
		File fileWithDataSerialized = new File("data-serialzed.txt");
		FileUtils.deleteQuietly(fileWithDataSerialized);
		FileUtils.writeByteArrayToFile(fileWithDataSerialized, baos.toByteArray());

		/* Deserialization */
		byte[] serData = FileUtils.readFileToByteArray(fileWithDataSerialized);
		ByteArrayInputStream bais = new ByteArrayInputStream(serData);
		Unpacker unpacker = msgpack.createUnpacker(bais);
		// Deserialize objects
		SimplePojo p3 = unpacker.read(SimplePojo.class);
		SimplePojo p4 = unpacker.read(SimplePojo.class);
		unpacker.close();

		/* Check */
		Assert.assertEquals(p1.member01, p3.member01);
		Assert.assertEquals("I don't want to be serialized", p3.member02);
		Assert.assertEquals(p2.member01, p4.member01);
		Assert.assertEquals("I don't want to be serialized", p4.member02);
	}

	@SuppressWarnings("boxing")
	private void printProcessingTime(String msg, long startTime) {
		System.out.printf("%s (%s ms)\n", msg, System.currentTimeMillis() - startTime);
	}
}
