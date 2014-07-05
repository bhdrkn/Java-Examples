package com.bahadirakin.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author bhdrkn
 *
 */
@XmlType(name = "person", namespace = "http://model.bahadirakin.com", propOrder = {"name", "surname"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="name", namespace="http://model.bahadirakin.com" , nillable=false, required=true, type=String.class)
	private String name;
	@XmlElement(name="surname", namespace="http://model.bahadirakin.com" , nillable=false, required=true, type=String.class)
	private String surname;

	public Person() {
		super();
	}	

	public Person(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", surname=" + surname + "]";
	}

}
