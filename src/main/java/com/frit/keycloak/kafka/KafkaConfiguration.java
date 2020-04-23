package com.frit.keycloak.kafka;

import org.keycloak.events.EventType;
import org.keycloak.events.admin.OperationType;

import java.util.Set;

public interface KafkaConfiguration {

    String getBootstrapServers();

    String getClientDnsLookup();

    String getMetadataMaxAge();

    String getBatchSize();

    String getAcknowledgments();

    String getLinger();

    String getRequestTimeout();

    String getDeliveryTimeout();

    String getClientId();

    String getSendBuffer();

    String getReceiveBuffer();

    String getMaxRequestSize();

    String getReconnectBackoff();

    String getReconnectBackoffMax();

    String getMaxBlock();

    String getBufferMemory();

    String getRetryBackoff();

    String getCompressionType();

    String getMetricsSampleWindow();

    String getMetricsNumberSamples();

    String getMetricsRecordingLevel();

    String getMetricReporterClasses();

    String getMaxInFlightRequestsPerConnection();

    String getRetriesConfig();

    String getConnectionsMaxIdle();

    String getPartitionerClass();

    String getInterceptorClasses();

    String getEnableIdempotence();

    String getTransactionalTimeout();

    String getTransactionalId();

    String getSecurityProviders();

    String getSchemaRegistryUrl();

    String getUserTopic();

    String getAdminTopic();

    Set<EventType> getExcludedEvents();

    Set<OperationType> getExcludedOperations();

}
