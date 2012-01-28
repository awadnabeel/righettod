package com.drighetto.spring25x.autowired;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

/**
 * Memory movies DB using the <b>Service</b> in order to be auto detected by
 * the Spring component detector and set the bean name to "moviesDB"
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
@Service("moviesDB")
public class MovieDB {
	/** Memory DB */
	private static HashMap<String, ArrayList<String>> MOVIES = new HashMap<String, ArrayList<String>>();

	/** DB Initialization */
	static {
		ArrayList<String> actionType = new ArrayList<String>();
		actionType.add("RAMBO I");
		actionType.add("RAMBO II");
		actionType.add("RAMBO III");
		actionType.add("RAMBO IV");
		MOVIES.put("ACTION", actionType);
		ArrayList<String> fantasyType = new ArrayList<String>();
		fantasyType.add("FINAL FANTASY I");
		fantasyType.add("FINAL FANTASY II");
		MOVIES.put("FANTASY", fantasyType);
	}

	/**
	 * Getter for the DB
	 * 
	 * @return The DB object
	 */
	public HashMap<String, ArrayList<String>> getMovies() {
		return MOVIES;
	}

}
