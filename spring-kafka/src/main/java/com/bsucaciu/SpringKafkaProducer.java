package com.bsucaciu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringKafkaProducer {

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaProducer.class, args);
    }
}
