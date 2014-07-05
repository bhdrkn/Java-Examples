package com.bahadirakin.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bahadirakin.model.HelloWorldMessage;
import com.bahadirakin.model.Person;

/**
 * 
 * @author bhdrkn
 * 
 */
public class MessageProducerImpl implements MessageProducer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(MessageProducerImpl.class);

	private static final String DEFAULT_HELLOWORLD_MESSAGE_FORMAT = "Hello to %s,%s";

	@Override
	public HelloWorldMessage produceHelloWorldMessage(Person person) {
		if (person == null) {
			logger.info("Person is null");
			return new HelloWorldMessage("Hello to everyone");
		}

		logger.info("Saying hello to person: {}", person);
		return new HelloWorldMessage(String.format(
				DEFAULT_HELLOWORLD_MESSAGE_FORMAT, person.getSurname(),
				person.getName()));
	}

}
