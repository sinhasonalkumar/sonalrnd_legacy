package com.sonal.java.memcache;

import java.io.IOException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

public class TestMemcache {
	public static void main(String[] args) {
		try {
			
		//	MemcachedClient cache = new MemcachedClient(AddrUtil.getAddresses("127.0.0.1:11211"));
			MemcachedClient cache = new MemcachedClient(AddrUtil.getAddresses("wbid-dev.vzjwvs.0001.use1.cache.amazonaws.com:11211"));
			
			
			addToCache("testKey","Hello Memcache",cache);
			System.out.println("------------------------");
			removeFromCache("testKey",cache);
			
		} catch (IOException e) {
			
		}
		

	}

	private static void addToCache(String key , String value , MemcachedClient cache) {
		
			// read the object from memory
			String cacheValue = (String )cache.get(key);
			System.out.println("CachedValue of " + key +" = " + cacheValue);

			// set a new object
			if(cacheValue == null){
				cache.set(key, 0, value);
				cacheValue = (String )cache.get(key);
				System.out.println("CachedValue of " + key +" = " + cacheValue);
			}
		
	}
	
	public static void  removeFromCache(String key , MemcachedClient cache){
		
			
			String cacheValue = (String )cache.get(key);
			System.out.println("CachedValue of " + key +" = " + cacheValue);
			System.out.println("Removing from Cache");
			cache.delete(key);
			cacheValue = (String )cache.get(key);
			System.out.println("CachedValue of " + key +" = " + cacheValue);
		
	}

}
