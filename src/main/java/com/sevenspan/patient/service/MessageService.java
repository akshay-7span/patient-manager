package com.sevenspan.patient.service;

import com.sevenspan.patient.dto.responsedto.ProducerResponse;
import com.sevenspan.patient.dto.responsedto.patientresponse.PatientStringResponse;

public interface MessageService {

    public PatientStringResponse publishMessage(String message);

    public ProducerResponse publishMessageWithCallBack(String message);
}
