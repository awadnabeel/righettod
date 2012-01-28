package com.righettod.jee6s3.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Sample synchronous listener that will be declared dynamically using JEE6 API.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class Sample04Listener implements HttpSessionAttributeListener {

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.servlet.http.HttpSessionBindingEvent)
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent hsbe) {
		System.out.println("Sample04Listener::AttributeAdded(" + hsbe.getName() + " = " + hsbe.getValue() + ")");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax.servlet.http.HttpSessionBindingEvent)
	 */
	@Override
	public void attributeRemoved(HttpSessionBindingEvent hsbe) {
		// Not used
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeReplaced(javax.servlet.http.HttpSessionBindingEvent)
	 */
	@Override
	public void attributeReplaced(HttpSessionBindingEvent hsbe) {
		// Not used
	}

}
