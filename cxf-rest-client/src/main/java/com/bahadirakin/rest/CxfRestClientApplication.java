package com.bahadirakin.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value = {"classpath*:cxf-client-context.xml"})
public class CxfRestClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CxfRestClientApplication.class, args);
    }

}
