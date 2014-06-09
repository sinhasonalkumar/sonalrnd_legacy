package com.sonal.testgroovy

class HelloGroovyWorld {
    def name
    def greet() { 
		"Hello ${name}" 
	}
    
    static main(args) {
        def helloWorld = new HelloGroovyWorld()
        helloWorld.name = "Groovy World !!"
        println helloWorld.greet()
    }
}
