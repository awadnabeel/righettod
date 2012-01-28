package com.drighetto.essai.springehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.util.Date;

/**
 * Cache sample of use<br>
 * Build a cache (limit objects count to number defined for
 * "maxElementsInMemory") and display cache evolution until cache will be
 * empty...
 * 
 * @author Dominique RIGHETTO<br>
 *         24 nov. 07<br>
 */
public class CacheSample01 {

	/**
	 * entry point
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         24 nov. 07<br>
	 * @param args
	 */
	@SuppressWarnings("boxing")
	public static void main(String[] args) {
		try {
			/* Activate the shutdown hook */
			System.setProperty("net.sf.ehcache.enableShutdownHook", "true");
			/* Create Spring Context and get cache object */
			ApplicationContext appCtx = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			Cache customCache = (Cache) appCtx.getBean("customCache");
			/* Fill cache */
			for (int i = 0; i < 10; i++) {
				Element elt = new Element(i, new CachedElement(i, "My Element "
						+ Integer.toString(i)));
				customCache.put(elt);
				System.out.printf("Add element : %s\n", elt.toString());
				Thread.sleep(1000);
			}
			/* Display Info */
			System.out.printf("Cache size         : %s\n", customCache
					.getKeys().size());
			System.out.printf("Cache status       : %s\n", customCache
					.getStatus().toString());
			System.out.printf("Cache memory value : %s\n", customCache
					.getKeys().toString());
			/* Iteration with EXPLICIT "evictExpiredElements()" invocation */
			// while (customCache.getSize() > 0) {
			// System.out.printf(
			// "%s - Cache size : %s - ElementIdList : %s\n",
			// DateFormat.getTimeInstance().format(new Date()),
			// customCache.getKeys().size(), customCache.getKeys()
			// .toString());
			// customCache.evictExpiredElements();
			// Thread.sleep(1000);
			// }
			/* Iteration with IMPLICIT "evictExpiredElements()" invocation */
			while (customCache.getKeysWithExpiryCheck().size() > 0) {
				System.out.printf(
						"%s - Cache size : %s - ElementIdList : %s\n",
						DateFormat.getTimeInstance().format(new Date()),
						customCache.getKeysWithExpiryCheck().size(),
						customCache.getKeysWithExpiryCheck().toString());
				Thread.sleep(1000);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
