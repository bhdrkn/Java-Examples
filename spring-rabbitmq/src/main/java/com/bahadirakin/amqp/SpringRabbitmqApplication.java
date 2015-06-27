package com.bahadirakin.amqp;

import com.bahadirakin.amqp.consumer.HelloMessageConsumer;
import com.bahadirakin.amqp.message.HelloMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringRabbitmqApplication implements CommandLineRunner{

    private final static String QUEUE = "my-test-queue";
    private final static String ROUTE_KEY = "my-route-key";
    private static final String EXCHANGE = "my-direct-exchange";

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Bean
    Queue queue() {
        return new Queue(QUEUE, true); // Durable
    }

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Binding binding(final Queue queue, final Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTE_KEY).noargs();
    }

    @Bean
    HelloMessageConsumer helloMessageConsumer(){
        return new HelloMessageConsumer();
    }

    @Bean
    MessageListenerAdapter messageListenerAdapter(HelloMessageConsumer helloMessageConsumer){
        return new MessageListenerAdapter(helloMessageConsumer, "onHelloMessage");
    }

    @Bean
    SimpleMessageListenerContainer simpleMessageListenerContainer(final ConnectionFactory connectionFactory,
                                                                         final MessageListenerAdapter messageListenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE);
        container.setMessageListener(messageListenerAdapter);
        return container;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringRabbitmqApplication.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {
        final HelloMessage helloMessage = new HelloMessage();
        helloMessage.setMessage("Hello");
        helloMessage.setFrom("bhdrkn");

        this.amqpTemplate.convertAndSend(EXCHANGE, ROUTE_KEY, helloMessage);
    }
}
