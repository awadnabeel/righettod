package com.drighetto.easymock;

import java.io.IOException;

/**
 * Simple class defining a currency
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class Currency {
	/** Currency unit */
	private String units;

	/** Currency amount */
	private long amount;

	/** Currency cents */
	private int cents;

	/**
	 * 
	 * Constructor
	 * 
	 * @param amount Currency amount
	 * @param code Currency code
	 */
	public Currency(double amount, String code) {
		this.units = code;
		setAmount(amount);
	}

	/**
	 * Amout setter
	 * 
	 * @param amount Amount to set
	 */
	private void setAmount(double amount) {
		this.amount = new Double(amount).longValue();
		this.cents = (int) ((amount * 100.0) % 100);
	}

	/**
	 * Convertion method to EURO
	 * 
	 * @param converter Exchange rate
	 * @return A EURO currency
	 */
	public Currency toEuros(ExchangeRate converter) {
		Currency c = null;
		if ("EUR".equals(this.units)) {
			c = this;
		} else {
			double input = this.amount + this.cents / 100.0;
			double rate;
			try {
				rate = converter.getRate(this.units, "EUR");
				double output = input * rate;
				c = new Currency(output, "EUR");
			} catch (IOException ex) {
				//We explicitly do nothing here....
			}
		}

		return c;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Currency) {
			Currency other = (Currency) o;
			return this.units.equals(other.units) && this.amount == other.amount && this.cents == other.cents;
		}
		return false;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.amount + "." + Math.abs(this.cents) + " " + this.units;
	}

}
