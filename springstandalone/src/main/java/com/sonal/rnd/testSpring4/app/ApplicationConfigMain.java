package com.sonal.rnd.testSpring4.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.sonal.rnd.testSpring4.dao.EntityFetcher;

@ComponentScan(basePackages ="com.sonal.rnd.testSpring4")
@Configuration
@EnableAspectJAutoProxy
public class ApplicationConfigMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfigMain.class);
		//IService service = (ServiceImpl) applicationContext.getBean(ServiceImpl.class);
		//service.dosomething();
		testHibernateProxyInitializer(applicationContext);
	}
	
	public static void testHibernateProxyInitializer(ApplicationContext applicationContext){
		EntityFetcher fetchedEntity = applicationContext.getBean(EntityFetcher.class);
		String entityId = "1";
		Object fetchEntity = fetchedEntity.findEntity(entityId);
	}
}
