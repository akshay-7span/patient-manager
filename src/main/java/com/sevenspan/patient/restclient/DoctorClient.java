package com.sevenspan.patient.restclient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "doctor")
public interface DoctorClient {
}
