package com.frit.keycloak.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.keycloak.Config;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.OperationType;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@SuppressWarnings("unchecked")
public class KafkaProperties extends Properties implements KafkaConfiguration {

    private static final String EXCLUDE_EVENTS_PROPERTY = "exclude.events";

    private static final String EXCLUDE_OPERATIONS_PROPERTY = "exclude.operations";

    private static final String SCHEMA_REGISTRY_URL_PROPERTY = "schema.registry.url";

    private static final String TOPIC_EVENT_USER_PROPERTY = "topic.event.user";

    private static final String TOPIC_EVENT_ADMIN_PROPERTY = "topic.event.admin";

    public KafkaProperties(Config.Scope config) {
        put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG));
        put(ProducerConfig.RETRIES_CONFIG, config.get(ProducerConfig.RETRIES_CONFIG));
        put(ProducerConfig.BATCH_SIZE_CONFIG, config.get(ProducerConfig.BATCH_SIZE_CONFIG));
        put(ProducerConfig.LINGER_MS_CONFIG, config.get(ProducerConfig.LINGER_MS_CONFIG));
        put(ProducerConfig.BUFFER_MEMORY_CONFIG, config.get(ProducerConfig.BUFFER_MEMORY_CONFIG));
        put(SCHEMA_REGISTRY_URL_PROPERTY, config.get(SCHEMA_REGISTRY_URL_PROPERTY));
        put(TOPIC_EVENT_USER_PROPERTY, config.get(TOPIC_EVENT_USER_PROPERTY));
        put(TOPIC_EVENT_ADMIN_PROPERTY, config.get(TOPIC_EVENT_ADMIN_PROPERTY));

        String[] excludedEvents = config.getArray(EXCLUDE_EVENTS_PROPERTY);
        if (excludedEvents != null) {
            Set<EventType> types = new HashSet<>();
            for (String event : excludedEvents) {
                types.add(EventType.valueOf(event));
            }
            put(EXCLUDE_EVENTS_PROPERTY, types);
        }

        String[] excludedOperations = config.getArray(EXCLUDE_OPERATIONS_PROPERTY);
        if (excludedOperations != null) {
            Set<OperationType> types = new HashSet<>();
            for (String operation : excludedOperations) {
                types.add(OperationType.valueOf(operation));
            }
            put(EXCLUDE_OPERATIONS_PROPERTY, types);
        }
    }

    @Override
    public String getBootstrapServers() {
        return getProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG);
    }

    @Override
    public String getRetries() {
        return getProperty(ProducerConfig.RETRIES_CONFIG);
    }

    @Override
    public String getBatchSize() {
        return getProperty(ProducerConfig.BATCH_SIZE_CONFIG);
    }

    @Override
    public String getLinger() {
        return getProperty(ProducerConfig.LINGER_MS_CONFIG);
    }

    @Override
    public String getBufferMemory() {
        return getProperty(ProducerConfig.BUFFER_MEMORY_CONFIG);
    }

    @Override
    public String getSchemaRegistryUrl() {
        return getProperty(SCHEMA_REGISTRY_URL_PROPERTY);
    }

    @Override
    public String getUserTopic() {
        return getProperty(TOPIC_EVENT_USER_PROPERTY);
    }

    @Override
    public String getAdminTopic() {
        return getProperty(TOPIC_EVENT_ADMIN_PROPERTY);
    }

    @Override
    public Set<EventType> getExcludedEvents() {
        return (Set<EventType>) getOrDefault(EXCLUDE_EVENTS_PROPERTY, new HashSet<>());
    }

    @Override
    public Set<OperationType> getExcludedOperations() {
        return (Set<OperationType>) getOrDefault(EXCLUDE_OPERATIONS_PROPERTY, new HashSet<>());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append("--- Kafka Properties ---")
                .append("\n");
        Enumeration<?> keys = propertyNames();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            builder.append(key);
            builder.append(" = ");
            builder.append(get(key));
            if (keys.hasMoreElements()) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
