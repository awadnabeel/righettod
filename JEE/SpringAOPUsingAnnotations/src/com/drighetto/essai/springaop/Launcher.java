package com.drighetto.essai.springaop;

import com.drighetto.essai.springaop.bean.SimpleBean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Sample launcher
 * 
 * @author Dominique RIGHETTO<br>
 *         23 mars 07<br>
 */
public class Launcher {

	/**
	 * Entry point
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         23 mars 07<br>
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		try {

			// Initialize Spring context
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			
			//Get bean from Spring context & call simple bean
			SimpleBean sb = (SimpleBean)context.getBean("simpleBean");
			System.out.println("---[Call method : sb.sayHello(\"Dom\") ]---");
			sb.sayHello("Dom");
			System.out.println("\n---------");
			System.out.println("----------------------------");
			System.out.println("---------\n");
			System.out.println("---[Call method : sb.sayHello() ]---");			
			System.out.println(sb.sayHello());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
