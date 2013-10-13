package com.bahadirakin.helloworld.v2;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HelloResponse {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
