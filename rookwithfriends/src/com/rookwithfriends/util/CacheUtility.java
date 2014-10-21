package com.rookwithfriends.util;

import java.util.Collections;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

public class CacheUtility {
	private Cache cache;
	
	public CacheUtility(){
		createCache();
		
	}
	
	private void createCache(){
        try {
            CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
            cache = cacheFactory.createCache(Collections.emptyMap());
        } catch (CacheException e) {
            // ...
        }
	}
	
	public Cache getCache(){
		return cache;
	}
	
	public void put(Object key, Object value){
		cache.put(key, value);
	}
	
	public Object get(Object key){
		return cache.get(key);
	}

}
