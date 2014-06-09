package com.sonal.rnd.testSpring4.aop.aspects;

import java.util.Collection;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sonal.rnd.testSpring4.cache.CacheObjectAgent;

@Aspect
@Component
public class HibernateAspect {

	@Transactional
	@Around("@annotation(com.sonal.rnd.testSpring4.annotation.SonalCacheRead)")
	public Object findEntity(ProceedingJoinPoint proceedingJoinPoint){
		Object[] methodArguments = proceedingJoinPoint.getArgs();
		String entityId = (String)methodArguments[0];
		Object objectReturned = CacheObjectAgent.findObject(entityId);
		try {
			
			if(objectReturned == null){
				System.out.println("Entity Not Found In Cache with Id = " + entityId + ".....Going To Call DB To Fetch It");
				objectReturned = proceedingJoinPoint.proceed();
				System.out.println("Entity with Id : " + entityId + "Cached Now " );
				CacheObjectAgent.cacheObject(entityId, objectReturned);
			}
			
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		return objectReturned;
	}
	
	
	public Object initProxy(Object proxy){
		if (proxy instanceof Collection<?>) {
			Collection<?> proxies = (Collection<?>) proxy;
			for(Object curProxy: proxies){
				if(!Hibernate.isInitialized(curProxy)){
					Hibernate.initialize(curProxy);
				}
			}
			
		}else{
			if(!Hibernate.isInitialized(proxy)){
				Hibernate.initialize(proxy);
			}
		}
		
		
		return proxy;
	}
}
