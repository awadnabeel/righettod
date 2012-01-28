package com.drighetto.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Custom adapter to marshall/unmarshall integer value of the "stock" element
 * to/from the "available" flag in the Book object
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
@SuppressWarnings("boxing")
public class StockAdapter extends XmlAdapter<Integer, Boolean> {

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public Integer marshal(Boolean v) throws Exception {
		Integer value = 0;
		if (v != null && v.booleanValue()) {
			value = Integer.MAX_VALUE;
		}
		return value;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
	 */
	@Override
	public Boolean unmarshal(Integer v) throws Exception {
		return (v != null && v > 0);
	}

}
