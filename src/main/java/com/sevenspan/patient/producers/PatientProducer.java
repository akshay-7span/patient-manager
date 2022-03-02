package com.sevenspan.patient.producers;

import com.sevenspan.patient.dto.responsedto.ProducerResponse;
import com.sevenspan.patient.dto.responsedto.patientresponse.PatientResponse;
import org.springframework.stereotype.Component;

@Component
public interface PatientProducer {

    public void sendMessage(String msg);

    public ProducerResponse sendMessageWithCallBack(String msg);

    public void sendMessageJSON(PatientResponse patientResponse);
}
