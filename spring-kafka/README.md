## Spring Kafka Producer



### Running the application

The application can run in any IDE as long it has the `Lombok` plugin installed.

To run the application from the command line:

```bash
  mvn spring-boot:run
```


### Cluster Setup

The application currently supports `PLAINTEXT` and `SSL` connections with Apache Kafka. 


```yaml
cluster:
    bootstrapServers: "broker:9092"
    securityProtocol: "PLAINTEXT"
```

#### Topic Config

The topic will be created automatically if `cluster.autoCreateTopic` is set to `true`. Keystore certificate
must have permissions to create topics.

```yaml
cluster:
  autoCreateTopic: true
  topic:
    minIsr: 1
    partitions: 2
    replicationFactor: 1
    retentionMs: 3600000
    segmentMs: 86400000
    name: random-text
```

#### SSL Config

```yaml
cluster:
  ssl:
    key-store-location: "/path/to/keystore.jks"
    key-password: verySecretPassword
    key-store-password: verySecretPassword
    trust-store-location: "/path/to/truststore.jks"
    trust-store-password: verySecretPassword
```
#### Producer Config

```yaml
cluster:
  bootstrapServers: "broker:9092"
  producer:
    clientId: "spring-kafka-producer"
    securityProtocol: "PLAINTEXT"
```

#### Consumer Config

```yaml
cluster:
  bootstrapServers: "broker:9092"
  consumer:
    clientId: "spring-kafka-consumer"
    group-id: "com.bsucaciu.spring.kafka.consumer"
    securityProtocol: "SSL"
```

#### Prerequisites for running the application
* Java 11+
* Maven 3
