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
 * "maxElementsInMemory") and extend the "TimeToLive" of an single element and
 * display cache evolution until cache will be empty, all this in order to prove
 * that the "TimeToLive" can be modified/managed for a element...
 * 
 * @author Dominique RIGHETTO
 */
public class CacheSample03 {

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
			/* Fill cache with sa special customized element */
			// TimeToLive set to 30 seconds instead of 20 seconds for the others
			// element
			Element eltCustom = new Element(0, new CachedElement(0,
					"My Element " + Integer.toString(0)));
			eltCustom.setTimeToLive(30);
			// Add to cache
			customCache.put(eltCustom);
			System.out
					.printf(
							"Add CUSTOM element with 'TimeToLive' property set to %s seconds  : %s\n",
							eltCustom.getTimeToLive(), eltCustom.toString());
			/* Fill cache with standart element */
			for (int i = 1; i < 10; i++) {
				Element elt = new Element(i, new CachedElement(i, "My Element "
						+ Integer.toString(i)));
				customCache.put(elt);
				System.out
						.printf(
								"Add STANDARD element with 'TimeToLive' property set to %s seconds  : %s\n",
								elt.getTimeToLive(), elt.toString());
				Thread.sleep(1000);
			}
			/* Display Info */
			System.out.printf("Cache size         : %s\n", customCache
					.getKeys().size());
			System.out.printf("Cache status       : %s\n", customCache
					.getStatus().toString());
			System.out.printf("Cache memory value : %s\n", customCache
					.getKeys().toString());
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
