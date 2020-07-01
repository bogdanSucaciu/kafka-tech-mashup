package com.bsucaciu.streams;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Slf4j
public class WordCountConsumer {

    private static String RANDOM_TEXT_TOPIC = "word-counts";

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "random.text.consumer");
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,  StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        Thread shutdownHook = new Thread(consumer::close);
        Runtime.getRuntime().addShutdownHook(shutdownHook);

        consumer.subscribe(Collections.singletonList(RANDOM_TEXT_TOPIC));

        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            records.forEach(record ->   log.info("Consumed Message with: \n" + record.key() + " : " + record.value()));
        }
    }
}
