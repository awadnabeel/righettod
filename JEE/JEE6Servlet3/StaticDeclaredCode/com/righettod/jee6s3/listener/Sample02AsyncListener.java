package com.righettod.jee6s3.listener;

import java.io.IOException;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

/**
 * Sample asynchronous listener in order show which events can be trapped
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class Sample02AsyncListener implements AsyncListener {

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.AsyncListener#onComplete(javax.servlet.AsyncEvent)
	 */
	@Override
	public void onComplete(AsyncEvent evt) throws IOException {
		System.out.println("Sample02AsyncListener::OnComplete");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.AsyncListener#onError(javax.servlet.AsyncEvent)
	 */
	@Override
	public void onError(AsyncEvent evt) throws IOException {
		if (evt.getThrowable() != null)
			System.out.println("Sample02AsyncListener::OnError => " + evt.getThrowable().getMessage());
		else
			System.out.println("Sample02AsyncListener::OnError");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.AsyncListener#onStartAsync(javax.servlet.AsyncEvent)
	 */
	@Override
	public void onStartAsync(AsyncEvent evt) throws IOException {
		System.out.println("Sample02AsyncListener::OnStartAsync");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.AsyncListener#onTimeout(javax.servlet.AsyncEvent)
	 */
	@Override
	public void onTimeout(AsyncEvent evt) throws IOException {
		System.out.println("Sample02AsyncListener::OnTimeout");
	}

}
