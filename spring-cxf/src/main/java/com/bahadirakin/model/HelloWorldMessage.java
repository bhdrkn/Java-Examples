package com.bahadirakin.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "helloWorldMessage", namespace = "http://model.bahadirakin.com")
@XmlAccessorType(XmlAccessType.FIELD)
public class HelloWorldMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "message", namespace = "http://model.bahadirakin.com", nillable = false, required = true, type = String.class)
	private String message;

	public HelloWorldMessage() {
		super();
	}

	public HelloWorldMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HelloWorldMessage other = (HelloWorldMessage) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HelloWorldMessage [message=" + message + "]";
	}

}
