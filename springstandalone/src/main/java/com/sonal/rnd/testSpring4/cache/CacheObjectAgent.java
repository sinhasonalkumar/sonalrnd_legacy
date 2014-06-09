package com.sonal.rnd.testSpring4.cache;

import org.springframework.stereotype.Component;

@Component
public class CacheObjectAgent {

	public static Object findObject(String key) {
		System.out.println("Going To Find In Cache Node For Key : " + key);
		Object chachedObject = null;
		if (key.equalsIgnoreCase("1")) {
			chachedObject = new Object();
			System.out.println("Found Object In Cache with Key : " + key);
		}
		return chachedObject;
	}

	public static void cacheObject(String key, Object value) {
		System.out.println(value + " Has Been Cached....");
	}
}
