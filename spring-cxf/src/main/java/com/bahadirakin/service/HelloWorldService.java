package com.bahadirakin.service;

import java.io.Serializable;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

import com.bahadirakin.model.HelloWorldMessage;
import com.bahadirakin.model.Person;

@WebService(name="HelloWorldService", targetNamespace="http://service.bahadirakin.com")
@SOAPBinding(parameterStyle=ParameterStyle.BARE)
public interface HelloWorldService extends Serializable{

	@WebMethod(operationName="sayHelloWorldTo", action="sayHelloWorldTo")
	@WebResult(name="helloWorldMessage", partName="helloWorldMessage", targetNamespace="http://model.bahadirakin.com")
	HelloWorldMessage sayHelloWorldTo(@WebParam(partName = "person", name = "person", targetNamespace = "http://model.bahadirakin.com/" ) Person person);
}
