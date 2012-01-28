package com.drighetto.rest.application;

import com.drighetto.rest.restlet.RestletOne;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.Router;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition of the REST application
 * 
 * @author Dominique RIGHETTO<br>
 *         27 janv. 08<br>
 */
public class RestApplication extends Application {

	/**
	 * Default Constructor
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         27 janv. 08<br>
	 */
	public RestApplication() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         27 janv. 08<br>
	 * @param context
	 *            Application context
	 */
	public RestApplication(Context context) {
		// Initialize root application context
		super(context);
	}

	/**
	 * @see org.restlet.Application#createRoot()
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public synchronized Restlet createRoot() {
		// Create a root router
		Router router = new Router(getContext());

		// Create a RESTlet handler with working attributes list
		List<String> workingAttributeList = new ArrayList<String>();
		workingAttributeList.add("user");
		workingAttributeList.add("action");
		RestletOne restletOne = new RestletOne(workingAttributeList);

		// Attach the handler to the root router
		router.attach("/", restletOne);
		router.attach("/users", restletOne);
		router.attach("/users/{user}", restletOne);
		router.attach("/users/{user}/actions", restletOne);
		router.attach("/users/{user}/actions/{action}", restletOne);

		// Return the root router
		return router;
	}

}
