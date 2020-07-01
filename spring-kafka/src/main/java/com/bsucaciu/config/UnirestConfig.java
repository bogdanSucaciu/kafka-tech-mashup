package com.bsucaciu.config;

import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnirestConfig {

    @Autowired
    public UnirestConfig() {
        Unirest.setObjectMapper(new ObjectMapper() {
            com.fasterxml.jackson.databind.ObjectMapper mapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            @SneakyThrows
            public String writeValue(Object value) {
                return mapper.writeValueAsString(value);
            }

            @SneakyThrows
            public <T> T readValue(String value, Class<T> valueType) {
                return mapper.readValue(value, valueType);
            }
        });
    }
}
