package com.springweb.rnd.services.memcache;



import java.io.IOException;

import org.springframework.stereotype.Component;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

@Component
public class TestMemcache {
	public  void testCache() {
		try {
			
		//	MemcachedClient cache = new MemcachedClient(AddrUtil.getAddresses("127.0.0.1:11211"));
			MemcachedClient cache = new MemcachedClient(AddrUtil.getAddresses("wbid-dev.vzjwvs.0001.use1.cache.amazonaws.com:11211"));
			
			
			addToCache("testKey","Hello MemcacheAndElasticCahce",cache);
			System.out.println("------------------------");
			removeFromCache("testKey",cache);
			
		} catch (IOException e) {
			
		}
		

	}
	
	public void testAdd(){
		try {
//			MemcachedClient cache = new MemcachedClient(AddrUtil.getAddresses("127.0.0.1:11211"));
			MemcachedClient cache = new MemcachedClient(AddrUtil.getAddresses("wbid-dev.vzjwvs.0001.use1.cache.amazonaws.com:11211"));
			addToCache("testKey","Hello MemcacheAndElasticCahce",cache);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String testRead(){
		String cacheValue = null;
		try {
//			MemcachedClient cache = new MemcachedClient(AddrUtil.getAddresses("127.0.0.1:11211"));
			MemcachedClient cache = new MemcachedClient(AddrUtil.getAddresses("wbid-dev.vzjwvs.0001.use1.cache.amazonaws.com:11211"));
		    cacheValue = (String )cache.get("testKey");
			System.out.println("CachedValue of " + "testKey" +" = " + cacheValue);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cacheValue;
	}
	
	
	public void testRemove(){
		try {
//			MemcachedClient cache = new MemcachedClient(AddrUtil.getAddresses("127.0.0.1:11211"));
			MemcachedClient cache = new MemcachedClient(AddrUtil.getAddresses("wbid-dev.vzjwvs.0001.use1.cache.amazonaws.com:11211"));
			removeFromCache("testKey",cache);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addToCache(String key , String value , MemcachedClient cache) {
		
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
	
	public  void  removeFromCache(String key , MemcachedClient cache){
		
			
			String cacheValue = (String )cache.get(key);
			System.out.println("CachedValue of " + key +" = " + cacheValue);
			System.out.println("Removing from Cache");
			cache.delete(key);
			cacheValue = (String )cache.get(key);
			System.out.println("CachedValue of " + key +" = " + cacheValue);
		
	}

}
