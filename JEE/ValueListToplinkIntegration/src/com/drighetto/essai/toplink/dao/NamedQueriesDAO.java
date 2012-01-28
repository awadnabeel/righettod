package com.drighetto.essai.toplink.dao;

import com.drighetto.essai.toplink.pojo.Developer;

import oracle.toplink.sessions.Session;
import oracle.toplink.tools.sessionmanagement.SessionManager;

import java.util.Vector;

/**
 * Toplink sample entry point using NamedQueriesDAO defined at descriptor level
 * 
 * @author Dominique RIGHETTO<br>
 *         5 avr. 07<br>
 */
public class NamedQueriesDAO {

	/** Toplink Session */
	private Session session;

	/**
	 * 
	 * Constructor
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         16 juil. 07<br>
	 */
	public NamedQueriesDAO() {
		// Get Toplink session (connection to DB)
		this.session = SessionManager.getManager().getSession("Session");
	}

	/**
	 * Getter for session<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         16 juil. 07<br>
	 * @return the session
	 */
	public Session getSession() {
		return this.session;
	}

	/**
	 * Method to extract developer by name and level if
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         16 juil. 07<br>
	 * @param name
	 *            Developer name
	 * @param idLevel
	 *            Level ID
	 * @return a Developer
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Developer extractDeveloper(String name, String idLevel)
			throws Exception {

		// Create named query parameters values array
		Vector<String> params = new Vector<String>();
		params.add(name);
		params.add(idLevel);
		// Call named query by name with parameters
		Object obj = this.session.executeQuery("findDevelopers",
				Developer.class, params);
		return (obj == null) ? null : (Developer) obj;
	}

}
