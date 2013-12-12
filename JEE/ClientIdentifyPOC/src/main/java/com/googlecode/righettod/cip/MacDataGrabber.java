package com.googlecode.righettod.cip;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.righettod.cip.vo.ClientInformation;

/**
 * Data grabber specialization for MAC OS.<br>
 * TODO: To implements
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class MacDataGrabber extends DataGrabber {

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.googlecode.righettod.cip.DataGrabber#grabFromFileSystem()
	 */
	@Override
	public List<ClientInformation> grabFromFileSystem() {
		return new ArrayList<>();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.googlecode.righettod.cip.DataGrabber#grabFromRegistry()
	 */
	@Override
	public List<ClientInformation> grabFromRegistry() {
		return new ArrayList<>();
	}

}
