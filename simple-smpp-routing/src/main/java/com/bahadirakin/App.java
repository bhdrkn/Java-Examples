package com.bahadirakin;

import com.bahadirakin.dao.SmDao;
import com.bahadirakin.entities.ShortMessage;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Application is going to be started");
        final AbstractApplicationContext applContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        applContext.registerShutdownHook();
        System.out.println("Application is started");

        SmDao dao = applContext.getBean(SmDao.class);

        final ShortMessage shortMessage = new ShortMessage();
        shortMessage.setMessage("Hello, World!");
        shortMessage.setDestinationAddress("05323332211");
        shortMessage.setDestinationNpi((byte) 0);
        shortMessage.setDestinationTon((byte) 0);

        dao.persist(shortMessage);

        System.in.read();


    }
}
