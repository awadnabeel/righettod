package com.righettod.jee6s3.runnable;

import javax.servlet.AsyncContext;

/**
 * Simple thread implementation in order to show async processing
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class RequestProcessor implements Runnable {

	/** Async context */
	private AsyncContext asyncContext = null;

	/**
	 * Constructor
	 * 
	 * @param asyncContext
	 *        Async context
	 */
	public RequestProcessor(AsyncContext asyncContext) {
		super();
		this.asyncContext = asyncContext;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// Perform processing (display thread name here)
		System.out.printf("RequestProcessor::The current thread is '%s'\n", Thread.currentThread().getName());
		// Use response to send content to client
		try {
			// Make a explicit pause in order to show threads processing
			// behavior
			Thread.sleep(5000);
			// Send data
			this.asyncContext.getResponse().getWriter().print("Hello World From Async ;o)");
			this.asyncContext.getResponse().flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the response stream and flag the async process as complete
			this.asyncContext.complete();
		}
	}

}
