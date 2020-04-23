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
        put(ProducerConfig.CLIENT_DNS_LOOKUP_CONFIG, config.get(ProducerConfig.CLIENT_DNS_LOOKUP_CONFIG));
        put(ProducerConfig.METADATA_MAX_AGE_CONFIG, config.get(ProducerConfig.METADATA_MAX_AGE_CONFIG));
        put(ProducerConfig.BATCH_SIZE_CONFIG, config.get(ProducerConfig.BATCH_SIZE_CONFIG));
        put(ProducerConfig.ACKS_CONFIG, config.get(ProducerConfig.ACKS_CONFIG));
        put(ProducerConfig.LINGER_MS_CONFIG, config.get(ProducerConfig.LINGER_MS_CONFIG));
        put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, config.get(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG));
        put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, config.get(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG));
        put(ProducerConfig.CLIENT_ID_CONFIG, config.get(ProducerConfig.CLIENT_ID_CONFIG));
        put(ProducerConfig.SEND_BUFFER_CONFIG, config.get(ProducerConfig.SEND_BUFFER_CONFIG));
        put(ProducerConfig.RECEIVE_BUFFER_CONFIG, config.get(ProducerConfig.RECEIVE_BUFFER_CONFIG));
        put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, config.get(ProducerConfig.MAX_REQUEST_SIZE_CONFIG));
        put(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG, config.get(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG));
        put(ProducerConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG, config.get(ProducerConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG));
        put(ProducerConfig.MAX_BLOCK_MS_CONFIG, config.get(ProducerConfig.MAX_BLOCK_MS_CONFIG));
        put(ProducerConfig.BUFFER_MEMORY_CONFIG, config.get(ProducerConfig.BUFFER_MEMORY_CONFIG));
        put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, config.get(ProducerConfig.RETRY_BACKOFF_MS_CONFIG));
        put(ProducerConfig.COMPRESSION_TYPE_CONFIG, config.get(ProducerConfig.COMPRESSION_TYPE_CONFIG));
        put(ProducerConfig.METRICS_SAMPLE_WINDOW_MS_CONFIG, config.get(ProducerConfig.METRICS_SAMPLE_WINDOW_MS_CONFIG));
        put(ProducerConfig.METRICS_NUM_SAMPLES_CONFIG, config.get(ProducerConfig.METRICS_NUM_SAMPLES_CONFIG));
        put(ProducerConfig.METRICS_RECORDING_LEVEL_CONFIG, config.get(ProducerConfig.METRICS_RECORDING_LEVEL_CONFIG));
        put(ProducerConfig.METRIC_REPORTER_CLASSES_CONFIG, config.get(ProducerConfig.METRIC_REPORTER_CLASSES_CONFIG));
        put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, config.get(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION));
        put(ProducerConfig.RETRIES_CONFIG, config.get(ProducerConfig.RETRIES_CONFIG));
        put(ProducerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, config.get(ProducerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG));
        put(ProducerConfig.PARTITIONER_CLASS_CONFIG, config.get(ProducerConfig.PARTITIONER_CLASS_CONFIG));
        put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, config.get(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG));
        put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, config.get(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG));
        put(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG, config.get(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG));
        put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, config.get(ProducerConfig.TRANSACTIONAL_ID_CONFIG));
        put(ProducerConfig.SECURITY_PROVIDERS_CONFIG, config.get(ProducerConfig.SECURITY_PROVIDERS_CONFIG));
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
    public String getClientDnsLookup() {
        return getProperty(ProducerConfig.CLIENT_DNS_LOOKUP_CONFIG);
    }

    @Override
    public String getMetadataMaxAge() {
        return getProperty(ProducerConfig.METADATA_MAX_AGE_CONFIG);
    }

    @Override
    public String getBatchSize() {
        return getProperty(ProducerConfig.BATCH_SIZE_CONFIG);
    }

    @Override
    public String getAcknowledgments() {
        return getProperty(ProducerConfig.ACKS_CONFIG);
    }

    @Override
    public String getLinger() {
        return getProperty(ProducerConfig.LINGER_MS_CONFIG);
    }

    @Override
    public String getRequestTimeout() {
        return getProperty(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG);
    }

    @Override
    public String getDeliveryTimeout() {
        return getProperty(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG);
    }

    @Override
    public String getClientId() {
        return getProperty(ProducerConfig.CLIENT_ID_CONFIG);
    }

    @Override
    public String getSendBuffer() {
        return getProperty(ProducerConfig.SEND_BUFFER_CONFIG);
    }

    @Override
    public String getReceiveBuffer() {
        return getProperty(ProducerConfig.RECEIVE_BUFFER_CONFIG);
    }

    @Override
    public String getMaxRequestSize() {
        return getProperty(ProducerConfig.MAX_REQUEST_SIZE_CONFIG);
    }

    @Override
    public String getReconnectBackoff() {
        return getProperty(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG);
    }

    @Override
    public String getReconnectBackoffMax() {
        return getProperty(ProducerConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG);
    }

    @Override
    public String getMaxBlock() {
        return getProperty(ProducerConfig.MAX_BLOCK_MS_CONFIG);
    }

    @Override
    public String getBufferMemory() {
        return getProperty(ProducerConfig.BUFFER_MEMORY_CONFIG);
    }

    @Override
    public String getRetryBackoff() {
        return getProperty(ProducerConfig.RETRY_BACKOFF_MS_CONFIG);
    }

    @Override
    public String getCompressionType() {
        return getProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG);
    }

    @Override
    public String getMetricsSampleWindow() {
        return getProperty(ProducerConfig.METRICS_SAMPLE_WINDOW_MS_CONFIG);
    }

    @Override
    public String getMetricsNumberSamples() {
        return getProperty(ProducerConfig.METRICS_NUM_SAMPLES_CONFIG);
    }

    @Override
    public String getMetricsRecordingLevel() {
        return getProperty(ProducerConfig.METRICS_RECORDING_LEVEL_CONFIG);
    }

    @Override
    public String getMetricReporterClasses() {
        return getProperty(ProducerConfig.METRIC_REPORTER_CLASSES_CONFIG);
    }

    @Override
    public String getMaxInFlightRequestsPerConnection() {
        return getProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION);
    }

    @Override
    public String getRetriesConfig() {
        return getProperty(ProducerConfig.RETRIES_CONFIG);
    }

    @Override
    public String getConnectionsMaxIdle() {
        return getProperty(ProducerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG);
    }

    @Override
    public String getPartitionerClass() {
        return getProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG);
    }

    @Override
    public String getInterceptorClasses() {
        return getProperty(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG);
    }

    @Override
    public String getEnableIdempotence() {
        return getProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG);
    }

    @Override
    public String getTransactionalTimeout() {
        return getProperty(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG);
    }

    @Override
    public String getTransactionalId() {
        return getProperty(ProducerConfig.TRANSACTIONAL_ID_CONFIG);
    }

    @Override
    public String getSecurityProviders() {
        return getProperty(ProducerConfig.SECURITY_PROVIDERS_CONFIG);
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
