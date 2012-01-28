package com.drighetto.spring25x.autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;

/**
 * Simple DB processor using autowired annotations :
 * <ul>
 * <li>Resource : Java EE 5 and Java 6 annotation like</li>
 * </ul>
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
public class DBProcessorV2 {

	/** DB object */
	@Resource(name = "moviesDB")
	private MovieDB movieDB = null;

	/** Spring Context */
	@Resource
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

	/**
	 * Display SimplePojo instance creation in order to show the "Configure"
	 * annotation behavior
	 */
	@SuppressWarnings("boxing")
	public void displaySimplePojoCreation() {
		for (int i = 0; i < 5; i++) {
			SimplePojo simplePojo = new SimplePojo();
			System.out.printf("%s:[%s] Msg -> %s\n",
					simplePojo.getInstanceId(), simplePojo.toString(),
					simplePojo.getMovieDB().getMovies().size());
		}
	}

}
