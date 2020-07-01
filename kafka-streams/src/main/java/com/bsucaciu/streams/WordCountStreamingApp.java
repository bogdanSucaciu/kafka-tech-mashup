package com.bsucaciu.streams;


import com.bsucaciu.streams.serde.RandomText;
import com.bsucaciu.streams.serde.RandomTextSerde;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

@Slf4j
public class WordCountStreamingApp {

    private static String WORD_REGEX = "\\W+";

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "broker:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, RandomTextSerde.class);

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, RandomText> textLines = builder.stream("random-text");
        textLines
                .map((key, value) -> KeyValue.pair(String.valueOf(value.getText().split(WORD_REGEX).length), value))
                .to("word-count");

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

    }
}
