package com.bahadirakin.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bahadirakin.helloworld.HelloRequest;

public class HelloProcessor implements Processor {

	private static final Logger logger = LoggerFactory
			.getLogger(HelloProcessor.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		final HelloRequest helloRequest = (HelloRequest) exchange.getIn()
				.getBody();
		logger.info("process");
		logger.info("FirstName: " + helloRequest.getFirstName());
		logger.info("LastName: " + helloRequest.getLastName());

	}

}
