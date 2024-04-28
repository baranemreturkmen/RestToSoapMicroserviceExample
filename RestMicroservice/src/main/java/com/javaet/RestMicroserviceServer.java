package com.javaet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RestMicroserviceServer {
    public static void main(String[] args) {
        SpringApplication.run(RestMicroserviceServer.class,args);
        System.out.println("Rest service is alive!");
    }
}