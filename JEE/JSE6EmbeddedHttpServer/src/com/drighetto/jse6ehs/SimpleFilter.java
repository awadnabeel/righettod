package com.drighetto.jse6ehs;

import java.io.IOException;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

/**
 * Simple implementation of a Filter
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class SimpleFilter extends Filter {

	/** Filter name */
	private String name = null;

	/**
	 * Constructor
	 * 
	 * @param name Filter name
	 */
	public SimpleFilter(String name) {
		super();
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.sun.net.httpserver.Filter#description()
	 */
	@Override
	public String description() {
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.sun.net.httpserver.Filter#doFilter(com.sun.net.httpserver.HttpExchange,
	 *      com.sun.net.httpserver.Filter.Chain)
	 */
	@Override
	public void doFilter(HttpExchange httpexchange, Chain chain) throws IOException {
		// Display a message to show the filter chain processing order
		System.out.printf("FILTER[%s] for URI : %s\n", this.name, httpexchange.getRequestURI());
		// Continue the filter chain...
		chain.doFilter(httpexchange);
	}

}
