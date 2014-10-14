package com.test.jvm;

import com.test.model.Class1;
import com.test.model.Class2;
import com.test.model.Class3;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		boolean flag = true;
		for(int i = 0; i<=500;i++){
			if(flag){
				init();
				flag = false;
				System.out.println(i);
			}
			else{
				Thread.sleep(300);
				flag = true;
			}
				
		
		}
		
	}
	
	public static void init(){
		for(int i = 0; i<=10000;i++){
			Class1 class1 = new Class1();
			Class2 class2 = new Class2();
			Class3 class3 = new Class3();
			class1 = null;
			class2 = null;
			class3 = null;
		}
		
	}

}
