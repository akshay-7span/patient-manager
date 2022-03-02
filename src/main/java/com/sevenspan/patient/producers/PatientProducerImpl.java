package com.sevenspan.patient.producers;

import com.sevenspan.patient.dto.responsedto.ProducerResponse;
import com.sevenspan.patient.dto.responsedto.patientresponse.PatientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class PatientProducerImpl implements PatientProducer{

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    MessageSource messageSource;

    @Override
    public void sendMessage(String msg) {
        kafkaTemplate.send("mytopic", msg);
    }

    @Override
    public ProducerResponse sendMessageWithCallBack(String msg) {

        ProducerResponse producerResponse = new ProducerResponse();

        ListenableFuture<SendResult<String, Object>> listenableFuture = kafkaTemplate.send("mytopic", msg);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {

            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                producerResponse.setTopicName(result.getRecordMetadata().topic());
                producerResponse.setOffsets(result.getRecordMetadata().offset());
                producerResponse.setPartitions(result.getRecordMetadata().partition());
                producerResponse.setTimeStamp(result.getRecordMetadata().timestamp());
            }
        });

        return producerResponse;
    }

    @Override
    public void sendMessageJSON(PatientResponse patientResponse) {
        kafkaTemplate.send("mytopic", patientResponse);
    }

}
