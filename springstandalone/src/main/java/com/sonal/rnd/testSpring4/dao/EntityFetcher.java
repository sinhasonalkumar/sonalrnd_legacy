package com.sonal.rnd.testSpring4.dao;

import org.springframework.stereotype.Repository;

import com.sonal.rnd.testSpring4.annotation.SonalCacheRead;

@Repository
public class EntityFetcher {

	@SonalCacheRead
	public Object findEntity(Object id){
		System.out.println("SQL executed...");
		Object entity = new Object();
		return entity;
	}
}
