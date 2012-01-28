package com.drighetto.lmb.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.drighetto.lmb.persistence.dao.ParkingRssDao;
import com.drighetto.lmb.persistence.model.Parking;

/**
 * Test cases for the Parking RSS DAO
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("boxing")
public class ParkingRssDaoTestCases {

	/**
	 * Test case 01 : Test FR lang
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDao01() throws Exception {
		long start = System.currentTimeMillis();
		List<Parking> parkings = ParkingRssDao.retrieveParkingInformations("fr");
		Assert.assertNotNull(parkings);
		Assert.assertEquals(27, parkings.size());
		Assert.assertEquals("Brasserie", parkings.get(0).getName());
		Assert.assertEquals("2 rue Emile Mousel", parkings.get(0).getAddress());
		Assert.assertEquals("Centre", parkings.get(0).getZone());
		Assert.assertEquals("360", parkings.get(0).getCapacity());
		System.out.printf("Test 'testDao01' performed in %s ms\n", (System.currentTimeMillis() - start));
	}

	/**
	 * Test case 02 : Test DE lang
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDao02() throws Exception {
		long start = System.currentTimeMillis();
		List<Parking> parkings = ParkingRssDao.retrieveParkingInformations("de");
		Assert.assertNotNull(parkings);
		Assert.assertEquals(27, parkings.size());
		Assert.assertEquals("Brasserie", parkings.get(0).getName());
		Assert.assertEquals("2 rue Emile Mousel", parkings.get(0).getAddress());
		Assert.assertEquals("Zentrum", parkings.get(0).getZone());
		Assert.assertEquals("360", parkings.get(0).getCapacity());
		System.out.printf("Test 'testDao02' performed in %s ms\n", (System.currentTimeMillis() - start));
	}

}
