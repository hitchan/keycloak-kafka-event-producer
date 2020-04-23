package com.frit.keycloak.kafka;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class KafkaEventProducerProviderFactory implements EventListenerProviderFactory {

    private static final String ID = "kafka";

    private KafkaConfiguration configuration;

    private KafkaProducer<String, Object> producer;

    @Override
    public EventListenerProvider create(KeycloakSession session) {
        return new KafkaEventProducerProvider(configuration, producer);
    }

    @Override
    public void init(Config.Scope config) {

        // Classloader workaround
        // https://stackoverflow.com/a/54118010/3352901

        try {
            ClassLoader original = Thread.currentThread().getContextClassLoader();
            Thread.currentThread().setContextClassLoader(null);
            KafkaProperties properties = new KafkaProperties(config);
            properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
            System.out.println(properties.toString());
            this.configuration = properties;
            this.producer = new KafkaProducer<>(properties);
            Thread.currentThread().setContextClassLoader(original);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new KafkaEventException(ex.getMessage(), ex);
        }
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {

    }

    @Override
    public void close() {
        if (this.producer != null) {
            this.producer.close();
        }
    }

    @Override
    public String getId() {
        return ID;
    }

}
