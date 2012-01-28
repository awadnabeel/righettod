package com.righettod.jee6s3.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Sample web request listener using JEE6 annotation to declare it
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@WebListener
public class Sample01Listener implements ServletRequestListener {

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.ServletRequestListener#requestDestroyed(javax.servlet.ServletRequestEvent)
	 */
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("Sample01Listener::RequestDestroy");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.ServletRequestListener#requestInitialized(javax.servlet.ServletRequestEvent)
	 */
	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("Sample01Listener::RequestInitialized");
	}

}
