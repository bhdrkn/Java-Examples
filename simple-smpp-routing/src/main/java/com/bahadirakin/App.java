package com.bahadirakin;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Application is going to be started");
        final AbstractApplicationContext applContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        applContext.registerShutdownHook();
        System.out.println("Application is started");

        System.in.read();

    }
}
