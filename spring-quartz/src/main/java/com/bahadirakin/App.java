package com.bahadirakin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        final ApplicationContext applicationContext = new ClassPathXmlApplicationContext
                ("classpath*:application-context.xml");

        Thread shutdownThread = new Thread() {

            public void run() {
                if (applicationContext instanceof ConfigurableApplicationContext) {
                    ((ConfigurableApplicationContext) applicationContext).close();
                }
            }
        };
        Runtime.getRuntime().addShutdownHook(shutdownThread);

        final SchedulerController schedulerController = applicationContext.getBean(SchedulerController.class);
        schedulerController.addJob(new DatabaseMonitor(new Date().toString(), "sql query"));
    }
}
