package com.bsucaciu.consumer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class ExampleConsumer {

    /**
     * Some boilerplate code that can be used to implement a Spring Kafka Consumer
     * @param event - the events consumer from a Kafka Topic
     */
    //@KafkaListener(topics = "#{'${cluster.topic.name}'}")
    public void consume(@Payload String event) {
        // TO-DO: Implement Consumer
    }
}
