package com.frit.keycloak.kafka;

import org.keycloak.events.EventType;
import org.keycloak.events.admin.OperationType;

import java.util.Set;

public interface KafkaConfiguration {

    String getBootstrapServers();

    String getRetries();

    String getBatchSize();

    String getLinger();

    String getBufferMemory();

    String getSchemaRegistryUrl();

    String getUserTopic();

    String getAdminTopic();

    Set<EventType> getExcludedEvents();

    Set<OperationType> getExcludedOperations();

}
