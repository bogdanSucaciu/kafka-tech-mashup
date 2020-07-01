# Starting up the components
Before starting up the platform there is a action required in order for non-Dockerized applications ( e.g: Spring Kafka, Kafka Streams, etc. ) to be able to connect to the Kafka Broker, and that is to add a new entry to the `/etc/hosts` file:

#### Open the `/etc/hosts` file with your preferred text editor:

e.g:
```
sudo vim /etc/hosts
```

Note: On Windows, this file can be found at `C:\windows\system32\drivers\etc\hosts`

#### Insert a new loopback address:
```
127.0.0.1       localhost
127.0.0.1       broker          #Loopback Address
255.255.255.255 broadcasthost
::1             localhost
```

To start up the components ( Apache Zookeer, Kafka Broker, Confluent Control Center, Schema Registry, Kafka Connect and MongoDB ) run the following command in the same directory the `docker-compose.yaml` file is placed:

```
docker-compose up -d
```

# Network Addresses
Each component can be accessed through the following network address:

```
Confluent Control Center UI - localhost:9021
Zookeeper                   - localhost:2181
Kafka Broker                - broker:9092
Kafka Connect REST API      - localhost:8083
Schema Registry REST API    - localhost:8081
MongoDB                     - localhost:27017
```

# KSQL Queries
KSQL Queries can be executed either through the `ksql-cli` container or through Confluent Control Center.

KSQL CLI Container
```
docker exec -it ksqldb-cli ksql
```

# MongoDB Queries
To execute MongoDB queries run the following command:

```
docker exec -it mongodb mongo -u admin -p admin
```

# Contributing
This is an open project! If you want to contribute to it, just raise a merge request! If you need ideas on how you can 
improve it, just let me know and maybe I can help!
