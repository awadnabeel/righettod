package com.drighetto.lmb.test;

import junit.framework.Assert;

import org.junit.Test;

import com.drighetto.lmb.persistence.dao.HowaldTunnelStatisticsDao;
import com.drighetto.lmb.persistence.model.Ride;
import com.drighetto.lmb.persistence.type.Rides;

/**
 * Test cases for the Howald Tunnel DAO
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("boxing")
public class HowaldTunnelStatisticsDaoTestCases {

	/**
	 * Test case 01 : Test for ride BERTRANGE_HOWALD
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDao01() throws Exception {
		long start = System.currentTimeMillis();
		Rides rs = Rides.BERTRANGE_HOWALD;
		Ride r = HowaldTunnelStatisticsDao.retrieveRideInformations(rs);
		Assert.assertNotNull(r);
		Assert.assertEquals(rs.getDriveWayCode(), r.getShortName());
		Assert.assertNotNull(r.getDisplayName());
		Assert.assertTrue(r.getCurrent() > 0);
		Assert.assertTrue(r.getAverage() > 0);
		Assert.assertTrue(r.getMaximum() > 0);
		System.out.printf("Test 'testDao01' performed in %s ms\n", (System.currentTimeMillis() - start));
	}

	/**
	 * Test case 02 : Test for ride BERCHEM_HOWALD
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDao02() throws Exception {
		long start = System.currentTimeMillis();
		Rides rs = Rides.BERCHEM_HOWALD;
		Ride r = HowaldTunnelStatisticsDao.retrieveRideInformations(rs);
		Assert.assertNotNull(r);
		Assert.assertEquals(rs.getDriveWayCode(), r.getShortName());
		Assert.assertNotNull(r.getDisplayName());
		Assert.assertTrue(r.getCurrent() > 0);
		Assert.assertTrue(r.getAverage() > 0);
		Assert.assertTrue(r.getMaximum() > 0);
		System.out.printf("Test 'testDao02' performed in %s ms\n", (System.currentTimeMillis() - start));
	}

	/**
	 * Test case 03 : Test for ride IRRGARTEN_HOWALD
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDao03() throws Exception {
		long start = System.currentTimeMillis();
		Rides rs = Rides.IRRGARTEN_HOWALD;
		Ride r = HowaldTunnelStatisticsDao.retrieveRideInformations(rs);
		Assert.assertNotNull(r);
		Assert.assertEquals(rs.getDriveWayCode(), r.getShortName());
		Assert.assertNotNull(r.getDisplayName());
		Assert.assertTrue(r.getCurrent() > 0);
		Assert.assertTrue(r.getAverage() > 0);
		Assert.assertTrue(r.getMaximum() > 0);
		System.out.printf("Test 'testDao03' performed in %s ms\n", (System.currentTimeMillis() - start));
	}

}
