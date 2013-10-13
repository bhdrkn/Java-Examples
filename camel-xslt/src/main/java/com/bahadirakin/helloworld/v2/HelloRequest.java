package com.bahadirakin.helloworld.v2;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HelloRequest {

	private String firstName;
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
