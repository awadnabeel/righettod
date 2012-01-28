package com.drighetto.jaxb;

import com.drighetto.jaxb.vo.Catalog.Book;

import javax.xml.bind.Unmarshaller;

/**
 * Custom listener to listen for unmarshalling events
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class UnmarshallerListener extends Unmarshaller.Listener {

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.xml.bind.Unmarshaller.Listener#afterUnmarshal(java.lang.Object,
	 *      java.lang.Object)
	 */
	@Override
	public void afterUnmarshal(Object target, Object parent) {
		if (target instanceof Book) {
			System.out.printf("After unmarshalling of Book[%s]\n", ((Book) target).getId());
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.xml.bind.Unmarshaller.Listener#beforeUnmarshal(java.lang.Object,
	 *      java.lang.Object)
	 */
	@Override
	public void beforeUnmarshal(Object target, Object parent) {
		if (target instanceof Book) {
			System.out.printf("Before unmarshalling of Book[%s]\n", ((Book) target).getId());
		}
	}

}
