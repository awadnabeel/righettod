package com.drighetto.easymock;

import java.io.IOException;

/**
 * Simple interface to define a exchange rate provider
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 *
 */
public interface ExchangeRate {
	/**
	 * Method to obtain a exchange rate (from a external source)
	 * @param inputCurrency Source currency 
	 * @param outputCurrency Target currency
	 * @return The exchange rate
	 * @throws java.io.IOException
	 */
	double getRate(String inputCurrency, String outputCurrency) throws IOException;

}
