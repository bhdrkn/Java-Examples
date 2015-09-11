package com.bahadirakin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringHibernate5Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringHibernate5Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Hello, World!");
    }
}
