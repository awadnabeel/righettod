package com.righettod.jee6s3.servlet;

import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.righettod.jee6s3.listener.Sample02AsyncListener;
import com.righettod.jee6s3.runnable.RequestProcessor;

/**
 * Sample asynchronous servlet using JEE6 annotation to declare it
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("serial")
@WebServlet(name = "Sample02AsyncServlet", value = "/Sample02AsyncServlet", asyncSupported = true)
public class Sample02AsyncServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.printf("Sample02AsyncServlet::The current thread is '%s'\n", Thread.currentThread().getName());
		// Pass in async mode
		AsyncContext aCtx = req.startAsync();
		aCtx.setTimeout(25000);// Timeout setted to 25 seconds
		// Add a async events listener
		aCtx.addListener(new Sample02AsyncListener());
		// Launch async processing using a dedicated thread
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
		executor.execute(new RequestProcessor(aCtx));
		// Never forget to close the executor in order to release all thread
		// objects link in memory...
		executor.shutdown();
		System.out.println("Sample02AsyncServlet::EndOfDoGet");
	}

}
