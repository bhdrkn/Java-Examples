package com.bahadirakin.helloworld.v2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class HelloWorldService {

	@WebMethod
	public String sayHello(@WebParam(name = "request") HelloRequest request) {
		return "Hello, " + request.getFirstName() + " " + request.getLastName();
	}
}
