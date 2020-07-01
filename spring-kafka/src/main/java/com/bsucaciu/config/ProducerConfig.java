package com.bsucaciu.config;

import com.bsucaciu.dto.RandomText;
import lombok.Data;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bsucaciu.config.SecurityConfig.createSecurityConfig;
import static org.apache.kafka.clients.producer.ProducerConfig.CLIENT_ID_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;

@Data
@Configuration
public class ProducerConfig {

    @Value("${cluster.bootstrapServers}")
    private List<String> bootstrapServers = new ArrayList<>(
            Collections.singletonList("broker:9092"));

    @Value("${cluster.producer.clientId}")
    private String clientId;

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

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        props.put(CLIENT_ID_CONFIG, this.clientId);
        props.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.putAll(createSecurityConfig(this.securityProtocol, this.keyPassword, this.keystoreLocation,
                this.keystorePassword, this.truststoreLocation, this.truststorePassword));
        return props;
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
