package com.drighetto.spring25x.autowired;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Simple POJO in order to show the use of "Configurable annotation
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
@Configurable(autowire = Autowire.BY_NAME, dependencyCheck = false, value = "simplePojo")
public class SimplePojo {

	/** DB Object */
	@Autowired(required = true)
	@Qualifier("moviesDB")
	private MovieDB movieDB = null;

	/** Instance ID */
	private final long instanceId = System.currentTimeMillis();

	/**
	 * Getter for the attribute movieDB
	 * 
	 * @return The value of movieDB
	 */
	public MovieDB getMovieDB() {
		return this.movieDB;
	}

	/**
	 * Setter for the attribute movieDB
	 * 
	 * @param movieDB
	 *            The new value
	 */
	public void setMovieDB(MovieDB movieDB) {
		this.movieDB = movieDB;
	}

	/**
	 * Getter for the attribute instanceId
	 * 
	 * @return The value of instanceId
	 */
	public long getInstanceId() {
		return this.instanceId;
	}

}
