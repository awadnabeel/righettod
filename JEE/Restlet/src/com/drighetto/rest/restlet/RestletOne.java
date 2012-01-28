package com.drighetto.rest.restlet;

import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Implementation of a RESTlet handler
 * 
 * @author Dominique RIGHETTO<br>
 *         27 janv. 08<br>
 */
public class RestletOne extends Restlet {

	/** Working attributes list */
	List<String> workingAttributesList = new ArrayList<String>();

	/**
	 * 
	 * Default Constructor
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         27 janv. 08<br>
	 * @param workingAttributesList
	 *            Working attributes list
	 */
	public RestletOne(List<String> workingAttributesList) {
		super();
		this.workingAttributesList = workingAttributesList;
	}

	/**
	 * @see org.restlet.Restlet#handle(org.restlet.data.Request,
	 *      org.restlet.data.Response)
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void handle(Request request, Response response) {
		// Print the available request attributes...
		StringBuilder sBuilder = new StringBuilder(
				"<b>Available request attributes (working attributes are in red) :</b> <br>");
		Set<String> attKeys = request.getAttributes().keySet();
		for (String key : attKeys) {
			if (this.workingAttributesList.contains(key)) {
				sBuilder.append("<font color='red'>");
			}
			sBuilder.append("[").append(key).append(" = ").append(
					request.getAttributes().get(key)).append("]<br>");
			if (this.workingAttributesList.contains(key)) {
				sBuilder.append("</font>");
			}
		}
		response.setEntity(sBuilder.toString().trim(), MediaType.TEXT_HTML);
	}

}
