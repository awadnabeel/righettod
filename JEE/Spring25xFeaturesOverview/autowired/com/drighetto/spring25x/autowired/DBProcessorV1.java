package com.drighetto.spring25x.autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;

/**
 * Simple DB processor using autowired annotations :
 * <ul>
 * <li>Autowired</li>
 * <li>Qualifier</li>
 * </ul>
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
public class DBProcessorV1 {

	/** DB object */
	@Autowired(required = true)
	@Qualifier("moviesDB")
	private MovieDB movieDB = null;

	/** Spring Context */
	@Autowired(required = true)
	private ApplicationContext springContext = null;

	/**
	 * Post construct processing
	 */
	@PostConstruct
	public void initProcessing() {
		System.out.printf("PostConstruct callback method invoked for %s\n",
				this.getClass().getName());
	}

	/**
	 * Post construct processing
	 */
	@PreDestroy
	public void closeProcessing() {
		System.out.printf("PreDestroy callback method invoked for %s\n", this
				.getClass().getName());
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
	 * Setter for the attribute springContext
	 * 
	 * @param springContext
	 *            The new value
	 */
	public void setSpringContext(ApplicationContext springContext) {
		this.springContext = springContext;
	}

	/**
	 * Display DB content
	 */
	public void displayDBContent() {
		for (String key : this.movieDB.getMovies().keySet()) {
			System.out.printf("<-------------TYPE : %s------------->\n", key);
			for (String value : this.movieDB.getMovies().get(key)) {
				System.out.printf("=>%s\n", value);
			}
		}

	}

	/**
	 * Display the number of the bean in the Spring context
	 */
	@SuppressWarnings("boxing")
	public void displaySpringCtxBeanCount() {
		System.out.printf("[Spring Context contains %s beans]\n",
				this.springContext.getBeanDefinitionCount());
	}

}
