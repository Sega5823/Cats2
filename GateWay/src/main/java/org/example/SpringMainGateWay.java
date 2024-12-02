package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMainGateWay {
    public static void main(String[] args) {
        SpringApplication.run(SpringMainGateWay.class, args);
        //String res = HTTPGet("http://localhost:8080/spring-boot-rest/api/cats/getCatByID?id=2");

    }
}