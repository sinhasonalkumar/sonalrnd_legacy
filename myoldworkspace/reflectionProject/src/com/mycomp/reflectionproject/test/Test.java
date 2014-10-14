package com.mycomp.reflectionproject.test;

public class Test {

	private void show(){
		System.out.println("Executing.... private void show()");
	}
	
	public void hello(String name){
		System.out.println("Hello !! " + name);
	}
	
	public String sayCustomWelcome(String name, String welcomeText){
		String message = welcomeText+ " !! " + name;
		System.out.println(message);
		return message;
	}
}
