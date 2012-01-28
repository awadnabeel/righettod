package com.righettod.jee6s3.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Sample synchronous filter using JEE6 annotation to declare it
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("unused")
@WebFilter(filterName = "Sample01SyncFilter", value = "/Sample01SyncServlet", asyncSupported = false)
public class Sample01SyncFilter implements Filter {

	/** Configuration for this filter */
	private FilterConfig cfg = null;

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		System.out.println("Sample01SyncFilter::Destroy");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) throws IOException, ServletException {
		System.out.println("Sample01SyncFilter::doFilter");
		fc.doFilter(req, resp);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig fc) throws ServletException {
		this.cfg = fc;
		System.out.println("Sample01SyncFilter::Init");
	}

}
