package com.googlecode.righettod.cip;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.googlecode.righettod.cip.type.InformationSource;
import com.googlecode.righettod.cip.vo.ClientInformation;

/**
 * Define behavior for data grabbing and offer possibility to specialize by OS.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("static-method")
public abstract class DataGrabber {

	/**
	 * Grab information from environment variables.
	 * 
	 * @return List of client informations.
	 */
	public List<ClientInformation> grabFromEnvironmentVariables() {
		List<ClientInformation> data = new ArrayList<>();

		for (Map.Entry<String, String> eVar : System.getenv().entrySet()) {
			data.add(new ClientInformation(InformationSource.ENVIRONMENT_VARIABLES, eVar.getKey() + "=" + defaultString(eVar.getValue())));
		}

		return data;
	}

	/**
	 * Grab key information from OS file system.
	 * 
	 * @return List of client informations.
	 */
	public abstract List<ClientInformation> grabFromFileSystem();

	/**
	 * Grab key information from OS registry.
	 * 
	 * @return List of client informations.
	 */
	public abstract List<ClientInformation> grabFromRegistry();

	/**
	 * Return the default string "" if a string is null or empty.
	 * 
	 * @param str Source string
	 * @return String
	 */
	protected String defaultString(String str) {
		return ((str == null) || str.trim().isEmpty()) ? "" : str;
	}

}
