package com.bsucaciu.config;

import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.apache.kafka.clients.CommonClientConfigs.SECURITY_PROTOCOL_CONFIG;
import static org.apache.kafka.common.config.SslConfigs.*;

@Configuration
public class SecurityConfig {

    private static final String SSL = "SSL";

    public static Map<String, String> createSecurityConfig(String securityProtocol, String keyPassword, String keystoreLocation, String keystorePassword, String truststoreLocation, String truststorePassword) {
        return SSL.equals(securityProtocol) ?
                 Map.of(
                    SSL_KEYSTORE_LOCATION_CONFIG, keystoreLocation,
                    SSL_KEYSTORE_PASSWORD_CONFIG, keystorePassword,
                    SSL_KEY_PASSWORD_CONFIG, keyPassword,
                    SSL_TRUSTSTORE_LOCATION_CONFIG, truststoreLocation,
                    SSL_TRUSTSTORE_PASSWORD_CONFIG, truststorePassword,
                    SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, "",
                    SECURITY_PROTOCOL_CONFIG, securityProtocol) :
                emptyMap();
    }
}
