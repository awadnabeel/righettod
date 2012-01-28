package com.drighetto.jaxb;

import com.drighetto.jaxb.vo.Catalog.Book;

import javax.xml.bind.Marshaller;

/**
 * Custom listener to listen for marshalling events
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class MarshallerListener extends Marshaller.Listener {

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.xml.bind.Marshaller.Listener#afterMarshal(java.lang.Object)
	 */
	@Override
	public void afterMarshal(Object source) {
		if (source instanceof Book) {
			System.out.printf("After marshalling of Book[%s]\n", ((Book) source).getId());
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.xml.bind.Marshaller.Listener#beforeMarshal(java.lang.Object)
	 */
	@Override
	public void beforeMarshal(Object source) {
		if (source instanceof Book) {
			System.out.printf("Before marshalling of Book[%s]\n", ((Book) source).getId());
		}
	}

}
