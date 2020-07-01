package com.bsucaciu.config;

import lombok.Data;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bsucaciu.config.SecurityConfig.createSecurityConfig;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

/**
 * Example Consumer Config class using a JSON Deserializer
 */
//@Configuration
@Data
public class ExampleConsumerConfig {
    @Value("${cluster.bootstrapServers}")
    private List<String> bootstrapServers = new ArrayList<>(
            Collections.singletonList("localhost:9092"));

    @Value("${cluster.consumer.clientId}")
    private String clientId;

    @Value("${cluster.consumer.clientId}")
    private String groupId;

    @Value("${cluster.securityProtocol}")
    private String securityProtocol;

    @Value("${cluster.ssl.key-store-location}")
    private String keystoreLocation;

    @Value("${cluster.ssl.key-store-password}")
    private String keystorePassword;

    @Value("${cluster.ssl.key-password}")
    private String keyPassword;

    @Value("${cluster.ssl.trust-store-location}")
    private String truststoreLocation;

    @Value("${cluster.ssl.trust-store-password}")
    private String truststorePassword;

    /*
   @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(AUTO_OFFSET_RESET_CONFIG, OffsetResetStrategy.LATEST.name().toLowerCase());
        props.put(BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        props.put(CLIENT_ID_CONFIG, this.clientId);
        props.put(GROUP_ID_CONFIG, this.groupId);
        props.putAll(createSecurityConfig(this.securityProtocol, this.keyPassword, this.keystoreLocation,
                this.keystorePassword, this.truststoreLocation, this.truststorePassword));

        return props;
    }

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        final JsonDeserializer<Object> jsonDeserializer = new JsonDeserializer<>();
        jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), jsonDeserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
    */

}
