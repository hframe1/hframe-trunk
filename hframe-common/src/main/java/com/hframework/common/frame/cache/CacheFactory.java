package com.hframework.common.frame.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.*;

public class CacheFactory {

	private static Map<String,Cache> cacheMap = new HashMap<String, Cache>();

	public static synchronized void put(Class clazz, String key,Object obj) {
		put(clazz.getName(),key,obj);
	}

	public static synchronized void put(String cacheName, String key,Object obj) {
		init(cacheName);
		Cache cache = cacheMap.get(cacheName);
		if (key != null && !key.equals("")){
			Element element = new Element(key, obj);
			cache.put(element);
		}
	}


	private static void init(String cacheName) {
		System.out.println("==>" + cacheName);
		if(!cacheMap.containsKey(cacheName)) {
			cacheMap.put(cacheName,CacheManager.getInstance().getCache(cacheName));
		}
	}

	public static synchronized Object get(Class clazz, String key) {
		return get(clazz.getName(),key);
	}

	public static synchronized Object get(String cacheName, String key) {
		init(cacheName);
		Cache cache = cacheMap.get(cacheName);
		Element element = null;
		try {
			element = cache.get(key);
		} catch (CacheException cacheException) {
			throw new DataRetrievalFailureException("ResourceCache failure: "
					+ cacheException.getMessage(), cacheException);
		}
		if (element == null)
			return null;

		return element.getObjectValue();
	}

	@SuppressWarnings("unchecked")
	public static synchronized Collection<String> getAll(Class clazz ) {
		return getAll(clazz.getName());
	}

	@SuppressWarnings("unchecked")
	public static synchronized Collection<String> getAll(String cacheName ) {
		init(cacheName);
		Cache cache = cacheMap.get(cacheName);
		Collection<String> resources;
		List<String> resclist = new ArrayList<String>();
		try {
			resources = cache.getKeys();
		} catch (IllegalStateException e) {
			throw new IllegalStateException(e.getMessage(), e);
		} catch (CacheException e) {
			throw new UnsupportedOperationException(e.getMessage(), e);
		}
		for (Iterator<String> localIterator = resources.iterator(); localIterator.hasNext();) {
			String key = localIterator.next();
			if (key != null){
				resclist.add(key);
			}
		}
		return resclist;
	}

	public static synchronized void remove(Class clazz,String key) {
		remove(clazz.getName(), key);
	}

	public static synchronized void remove(String cacheName,String key) {
		init(cacheName);
		Cache cache = cacheMap.get(cacheName);
		cache.remove(key);
	}

	public static synchronized void removeAll(Class clazz) {
		removeAll(clazz.getName());
	}

	public static synchronized void removeAll(String cacheName) {
		init(cacheName);
		Cache cache = cacheMap.get(cacheName);
		cache.removeAll();
		cache.clearStatistics();
		cache.flush();
	}

}
