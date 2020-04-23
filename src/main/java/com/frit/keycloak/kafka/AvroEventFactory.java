package com.frit.keycloak.kafka;

import com.frit.keycloak.kafka.event.Details;
import com.frit.keycloak.kafka.event.KeycloakAdminEvent;
import com.frit.keycloak.kafka.event.KeycloakEvent;
import org.keycloak.events.Event;
import org.keycloak.events.admin.AdminEvent;

import java.time.Instant;

public class AvroEventFactory implements EventFactory {

    @Override
    public KeycloakEvent create(Event event) {
        return KeycloakEvent.newBuilder()
                .setClientId(event.getClientId())
                .setDetails(event.getDetails())
                .setError(event.getError())
                .setIpAddress(event.getIpAddress())
                .setRealmId(event.getRealmId())
                .setSessionId(event.getSessionId())
                .setTime(Instant.ofEpochMilli(event.getTime()))
                .setType(event.getType() == null ? null : event.getType().name())
                .setUserId(event.getUserId())
                .build();
    }

    @Override
    public KeycloakAdminEvent create(AdminEvent event) {
        return KeycloakAdminEvent.newBuilder()
                .setTime(Instant.ofEpochMilli(event.getTime()))
                .setError(event.getError())
                .setOperationType(event.getOperationType() == null ? null : event.getOperationType().name())
                .setRealmId(event.getRealmId())
                .setRepresentation(event.getRepresentation())
                .setResourcePath(event.getResourcePath())
                .setResourceType(event.getResourceType() == null ? null : event.getResourceType().name())
                .setDetails(event.getAuthDetails() == null ? null : Details.newBuilder()
                        .setClientId(event.getAuthDetails().getClientId())
                        .setIpAddress(event.getAuthDetails().getIpAddress())
                        .setRealmId(event.getAuthDetails().getRealmId())
                        .setUserId(event.getAuthDetails().getUserId())
                        .build())
                .build();
    }

}
