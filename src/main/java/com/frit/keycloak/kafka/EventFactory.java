package com.frit.keycloak.kafka;

import org.keycloak.events.Event;
import org.keycloak.events.admin.AdminEvent;

public interface EventFactory {

    Object create(Event event);

    Object create(AdminEvent event);

}
