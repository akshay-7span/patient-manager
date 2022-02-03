package com.sevenspan.patient.dto.requestdto.patientdto;

import lombok.Data;

@Data
public class PatientFilterRequest {

    private Long doctorId;
    private String firstName;
    private Integer age;
    private String gender;
    private Long phoneNumber;
}
