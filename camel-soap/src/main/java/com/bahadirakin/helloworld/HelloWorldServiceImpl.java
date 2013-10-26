package com.bahadirakin.helloworld;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(endpointInterface = "com.bahadirakin.helloworld.HelloWorldService", name = "HelloWorldService", portName = "HelloWorldServicePort", serviceName = "HelloWorldServiceService", targetNamespace = "http://helloworld.bahadirakin.com/", wsdlLocation = "wsdl/HelloWorldService.wsdl")
public class HelloWorldServiceImpl implements HelloWorldService {

	@Override
	@WebMethod
	@WebResult(name = "sayHelloResponse", targetNamespace = "http://helloworld.bahadirakin.com/", partName = "sayHelloResponse")
	public String sayHello(
			@WebParam(name = "helloRequest", targetNamespace = "http://helloworld.bahadirakin.com/", partName = "helloRequest") HelloRequest helloRequest) {
		return "Hello, " + helloRequest.getFirstName() + " "
				+ helloRequest.getLastName();
	}

}
