package com.bsucaciu.config;

import lombok.Data;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.*;

import static com.bsucaciu.config.SecurityConfig.createSecurityConfig;
import static java.lang.Integer.parseInt;
import static java.lang.Short.*;
import static org.apache.kafka.common.config.TopicConfig.*;

@Configuration
@Data
public class AdminConfig {

    @Value("${cluster.bootstrapServers}")
    private List<String> bootstrapServers = new ArrayList<>(
            Collections.singletonList("localhost:9092"));

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

    @Value("${cluster.autoCreateTopic}")
    private boolean autoCreateTopic;

    @Value("${cluster.topic.name}")
    private String topicName;

    @Value("${cluster.topic.minIsr}")
    private String topicMinIsr;

    @Value("${cluster.topic.partitions}")
    private Integer topicPartitions;

    @Value("${cluster.topic.replicationFactor}")
    private Short topicReplicationFactor;

    @Value("${cluster.topic.retentionMs}")
    private String topicRetentionMs;

    @Value("${cluster.topic.segmentMs}")
    private String topicSegmentMs;

    @Value("${cluster.topic.cleanupPolicy}")
    private String cleanupPolicy;

    @Bean
    @ConditionalOnProperty(value = "config.autoCreateTopic", havingValue = "true")
    public KafkaAdmin admin() {
        Map<String, Object> props = new HashMap<>();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        props.put(AdminClientConfig.CLIENT_ID_CONFIG, this.clientId);
        props.putAll(createSecurityConfig(this.securityProtocol, this.keyPassword, this.keystoreLocation,
                this.keystorePassword, this.truststoreLocation, this.truststorePassword));
        KafkaAdmin kafkaAdmin = new KafkaAdmin(props);
        kafkaAdmin.setAutoCreate(autoCreateTopic);
        return kafkaAdmin;
    }

    @Bean
    @ConditionalOnProperty(value = "config.autoCreateTopic", havingValue = "true")
    public NewTopic topic() {
        return new NewTopic(topicName, topicPartitions, topicReplicationFactor)
                .configs(getTopicConfigs());
    }

    private Map<String, String> getTopicConfigs() {
        return Map.of(
                MIN_IN_SYNC_REPLICAS_CONFIG, topicMinIsr,
                CLEANUP_POLICY_CONFIG, cleanupPolicy,
                RETENTION_MS_CONFIG, topicRetentionMs,
                SEGMENT_MS_CONFIG, topicSegmentMs);
    }
}
