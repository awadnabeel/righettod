package com.righettod.jee6s3.listener;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.righettod.jee6s3.filter.Sample03SyncFilter;
import com.righettod.jee6s3.servlet.Sample03SyncServlet;

/**
 * Sample context listener used to declare filters/servlets/listeners
 * dynamically using JEE6 API.<br>
 * <br>
 * This listener must be declared statically !
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@WebListener
public class Sample03Listener implements ServletContextListener {

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Sample03Listener::ContextDestroyed");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Sample03Listener::ContextInitialized");

		ServletContext context = sce.getServletContext();

		/* Declare Servlet */
		// Declare Servlet into the ServletContext
		Class servlet = Sample03SyncServlet.class;
		javax.servlet.ServletRegistration.Dynamic servletConfiguration = context.addServlet("Sample03SyncServlet", servlet);
		// Configure the Servlet added
		servletConfiguration.addMapping("/Sample03SyncServlet");
		servletConfiguration.setInitParameter("msg", "Hello From Dynamic declared servlet ;o)");

		/* Declare Filter */
		// Declare Filter into the ServletContext
		Class filter = Sample03SyncFilter.class;
		javax.servlet.FilterRegistration.Dynamic filterConfiguration = context.addFilter("Sample03SyncFilter", filter);
		// Configure the Filter added
		EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST);
		filterConfiguration.addMappingForUrlPatterns(dispatcherTypes, false, "/Sample03SyncServlet/*");

		/* Declare Listener */
		// Declare FiListener into the ServletContext
		context.addListener("com.righettod.jee6s3.listener.Sample04Listener");
	}

}
