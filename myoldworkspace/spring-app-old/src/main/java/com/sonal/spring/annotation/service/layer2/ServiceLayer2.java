package com.sonal.spring.annotation.service.layer2;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.sonal.spring.annotation.exception.SonalsHandleException;
import com.sonal.spring.annotation.exception.SonalsLoggerAnnotation;

@Service
public class ServiceLayer2 {

	
	@SonalsLoggerAnnotation
	@SonalsHandleException	
	public void doSomething() throws IOException{
		System.out.println("Doing something in ServiceLayer2");
		//System.out.println(0 / 0)
		if(true){
			throw new IOException();	
		}		
		System.out.println("After Exception in ServiceLayer2");
	}
}
