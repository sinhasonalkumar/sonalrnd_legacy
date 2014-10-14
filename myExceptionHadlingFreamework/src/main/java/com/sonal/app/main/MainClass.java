package com.sonal.app.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sonal.app.service.internal.InternalService;
import com.sonal.util.exception.AppException;

@Component
public class MainClass {
	

	public static void main(String[] args){
		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		InternalService internalService = appContext.getBean(InternalService.class);
		try {
			internalService.callInternalService();
		} catch (Throwable e) {
			
			if (e instanceof AppException) {
				AppException appException = (AppException) e;
				System.out.println(appException.getErrorCode());
				System.out.println(appException.getErrorMessage());
				System.out.println(appException.getExceptionType());
				System.out.println(appException.getExceptionClass());
				System.out.println(appException.getRootCause());
				//appException.printStackTrace();
			}
		}
		
	}
}
