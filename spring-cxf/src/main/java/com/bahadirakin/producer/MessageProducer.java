package com.bahadirakin.producer;

import java.io.Serializable;

import com.bahadirakin.model.HelloWorldMessage;
import com.bahadirakin.model.Person;

/**
 * 
 * @author bhdrkn
 *
 */
public interface MessageProducer extends Serializable{

	HelloWorldMessage produceHelloWorldMessage(final Person person);
}
