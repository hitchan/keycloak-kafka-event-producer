# Kafka Event Producer for Keycloak

A Keycloak SPI that publishes events to Kafka.

# Build
```
./gradlew clean uberJar
```

# Deploy
* Register the following Avro schemas with your Schema Registry.
    * `src/main/avro/KeycloakAdminEvent.avsc`
    * `src/main/avro/KeycloakEvent.avsc`
* Deploy (copy) the UberJar 
    * From - `build/libs/kafka-event-producer-{VERSION}-uber.jar`
    * To - `{KEYCLOAK_HOME}/standalone/deployments`
* Edit your keycloak `standalone.xml` file (Located by default at `{KEYCLOAL_HOME}/standalone/configuration`) to configure Kafka Settings. The following section provides a sample configuration.

```
<spi name="eventsListener">
    <provider name="kafka" enabled="true">
        <properties>
            <property name="bootstrap.servers" value="http://localhost:9092"/>
            <property name="schema.registry.url" value="http://localhost:8081"/>
            <property name="retries" value="0"/>
            <property name="batch.size" value="0"/>
            <property name="linger.ms" value="1"/>
            <property name="buffer.memory" value="33554432"/>
            <property name="topic.event.user" value="streaming.keycloak.events.user"/>
            <property name="topic.event.admin" value="streaming.keycloak.events.admin"/>
        </properties>
    </provider>
</spi>
```

# Notes
* The name of the property directly corresponds to the kafka producer property name except for the following properties.
```
<property name="topic.event.user" value="Your.Kafka.Topic.For.User.Events"/>
<property name="topic.event.admin" value="Your.Kafka.Topic.For.Admin.Events"/>
```

# Contributing
* Pull requests are welcome!