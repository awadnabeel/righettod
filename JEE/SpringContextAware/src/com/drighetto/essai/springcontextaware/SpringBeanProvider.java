package com.drighetto.essai.springcontextaware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring Bean Provider - Retrieve a bean in the Spring context and give access
 * to Spring context for the bean non managed by Spring
 * 
 * @author Dominique RIGHETTO<br>
 *         7 janv. 08<br>
 */
public class SpringBeanProvider implements ApplicationContextAware {

	/** Spring context */
	private static ApplicationContext SPRING_CTX = null;

	/**
	 * 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 * 
	 * {@inheritDoc}
	 */
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		SPRING_CTX = ctx;
	}

	/**
	 * Method (shortcut) to retrieve a bean from Spring context
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         7 janv. 08<br>
	 * @param beanName
	 *            Name of the bean in the context
	 * @return a object
	 */
	public static Object getBean(String beanName) {
		return SPRING_CTX.getBean(beanName);
	}

	/**
	 * Method to access to Spring context
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         11 janv. 08<br>
	 * @return a reference to the Spring context
	 */
	public static ApplicationContext getSpringContext() {
		return SPRING_CTX;
	}

}
