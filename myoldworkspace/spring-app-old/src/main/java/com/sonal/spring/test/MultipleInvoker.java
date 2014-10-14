package com.sonal.spring.test;

import java.lang.reflect.Method;


public class MultipleInvoker {
	
	public static void invokeThis(Object theObject){
		try {
			Method [] methods = Class.forName(theObject.getClass().getName()).getMethods();
			
			for (int i = 0; i < methods.length; i++) {
				InvokeMultiple invokeMultiple = methods[i].getAnnotation(InvokeMultiple.class);
				if(invokeMultiple != null){
					int numberOfTimesToInvoke = invokeMultiple.numberOfTimesToInvoke();
					for (int j = 0; j < numberOfTimesToInvoke; j++) {
						methods[i].invoke(theObject, null);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
