package com.drighetto.essai.springcontextaware;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Sample entry point
 * 
 * @author Dominique RIGHETTO<br>
 *         7 janv. 08<br>
 */
@SuppressWarnings("unused")
public class RunSample {

	/**
	 * Entry point
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         7 janv. 08<br>
	 * @param args
	 */
	@SuppressWarnings("boxing")
	public static void main(String[] args) {
		try {
			// Initialize a Spring context
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
					"applicationContext.xml");

			// Get a Spring Bean from the bean provider and display value...
			SimplePojo simplePojoInstance = (SimplePojo) SpringBeanProvider
					.getBean("simplePojo");
			System.out.printf("Value of POJO message : %s\n",
					simplePojoInstance.getMessage());
			System.out.printf("Number of bean in the context : %s\n",
					SpringBeanProvider.getSpringContext()
							.getBeanDefinitionCount());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
