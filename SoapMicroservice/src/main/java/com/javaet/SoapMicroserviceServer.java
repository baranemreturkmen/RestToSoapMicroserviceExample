package com.javaet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoapMicroserviceServer {
    public static void main(String[] args) {
        SpringApplication.run(SoapMicroserviceServer.class,args);
        System.out.println("Soap service is alive!");
    }
}