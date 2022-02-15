package com.sevenspan.patient.scheduler;

import com.sevenspan.patient.service.PatientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@EnableAsync
public class Scheduler {

    @Autowired
    PatientService patientService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateStatusInactive() {
        patientService.updateStatusInactive();
    }
}
