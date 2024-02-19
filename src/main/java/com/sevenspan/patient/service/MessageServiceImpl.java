package com.sevenspan.patient.service;

import com.sevenspan.patient.dto.responsedto.ProducerResponse;
import com.sevenspan.patient.dto.responsedto.patientresponse.PatientStringResponse;
import com.sevenspan.patient.producers.PatientProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    PatientProducer patientProducer;

    @Autowired
    MessageSource messageSource;

    @Override
    public PatientStringResponse publishMessage(String message) {
        patientProducer.sendMessage(message);
        PatientStringResponse patientStringResponse=new PatientStringResponse();
        patientStringResponse.setMessage(messageSource.getMessage("record.message.published", null, Locale.US));
        return patientStringResponse;
    }

    @Override
    public ProducerResponse publishMessageWithCallBack(String message){
        return patientProducer.sendMessageWithCallBack(message);
    }
}
