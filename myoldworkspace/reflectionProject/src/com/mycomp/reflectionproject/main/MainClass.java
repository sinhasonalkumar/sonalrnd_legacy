package com.mycomp.reflectionproject.main;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import com.mycomp.reflectionproject.test.Test;

public class MainClass {
	
	public static void main(String[] args) throws Exception {
		reflectionTest();
	}
	
	private static void reflectionTest() throws Exception{
		Class<?> clazz = Test.class;
		Object newInstance = null;
		
		System.out.println(clazz.getName());
		Constructor<?>[] constructors = clazz.getConstructors();
		
		for(Constructor<?> constructor : constructors){
			System.out.println(constructor.getName());
			newInstance = constructor.newInstance(null);
		}
		
		
		
		Method[] methods = clazz.getMethods();
		
		for(Method curMethod : methods){
			System.out.println(curMethod);
			String curMethodName = curMethod.getName();
			Class<?> returnType = curMethod.getReturnType();
			Class<?>[] parameterTypes = curMethod.getParameterTypes();
			System.out.println(returnType);
			System.out.println(parameterTypes);
			
			curMethod.invoke(newInstance, "sonal");			
		}
	
		
	}
}
