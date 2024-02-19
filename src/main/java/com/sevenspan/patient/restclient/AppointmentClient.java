package com.sevenspan.patient.restclient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "appointment")
public interface AppointmentClient {
}
