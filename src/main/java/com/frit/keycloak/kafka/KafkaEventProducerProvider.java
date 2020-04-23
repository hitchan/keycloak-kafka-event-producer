package com.frit.keycloak.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;

public class KafkaEventProducerProvider implements EventListenerProvider {

    private final EventFactory factory;

    private final KafkaConfiguration configuration;

    private final KafkaProducer<String, Object> producer;

    public KafkaEventProducerProvider(KafkaConfiguration configuration, KafkaProducer<String, Object> producer) {
        this(configuration, producer, new AvroEventFactory());
    }

    public KafkaEventProducerProvider(KafkaConfiguration configuration, KafkaProducer<String, Object> producer, EventFactory factory) {
        this.configuration = configuration;
        this.producer = producer;
        this.factory = factory;
    }

    @Override
    public void onEvent(Event event) {
        if (isExcluded(event)) {
            return;
        }
        producer.send(new ProducerRecord<>(configuration.getUserTopic(), factory.create(event)));
    }

    @Override
    public void onEvent(AdminEvent event, boolean includeRepresentation) {
        if (isExcluded(event)) {
            return;
        }
        producer.send(new ProducerRecord<>(configuration.getAdminTopic(), factory.create(event)));
    }

    @Override
    public void close() {

    }

    private boolean isExcluded(Event event) {
        return event == null || configuration.getExcludedEvents().contains(event.getType());
    }

    private boolean isExcluded(AdminEvent event) {
        return event == null || configuration.getExcludedOperations().contains(event.getOperationType());
    }

}
