package com.bsucaciu.streams.serde;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RandomTextSerde extends Serdes.WrapperSerde<RandomText> {

    public RandomTextSerde() {
        super(new Serializer<RandomText>() {
            private Gson gson = new Gson();

            @Override
            public void configure(Map<String, ?> map, boolean b) {
            }

            @Override
            public byte[] serialize(String topic, RandomText data) {
                return gson.toJson(data).getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public void close() {
            }
        }, new Deserializer<RandomText>() {
            private Gson gson = new Gson();

            @Override
            public void configure(Map<String, ?> configs, boolean isKey) {

            }

            @Override
            public RandomText deserialize(String topic, byte[] data) {

                return gson.fromJson(new String(data), RandomText.class);
            }

            @Override
            public void close() {

            }
        });
    }
}
