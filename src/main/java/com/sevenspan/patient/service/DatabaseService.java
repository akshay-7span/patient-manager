package com.sevenspan.patient.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public interface DatabaseService extends HealthIndicator {

    public Health health();

    public int check();
}
