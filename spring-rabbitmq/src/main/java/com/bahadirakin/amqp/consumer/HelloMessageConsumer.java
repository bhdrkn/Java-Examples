package com.bahadirakin.amqp.consumer;

import com.bahadirakin.amqp.message.HelloMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloMessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(HelloMessageConsumer.class);

    public void onHelloMessage(final HelloMessage message) {
        logger.info("{} from {}", message.getMessage(), message.getFrom());
    }
}
