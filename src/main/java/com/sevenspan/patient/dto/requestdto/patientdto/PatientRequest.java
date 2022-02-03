package com.sevenspan.patient.dto.requestdto.patientdto;

import lombok.Data;

import java.sql.Date;

@Data
public class PatientRequest {

    private String patientId;
    private Long doctorId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Integer age;
    private String gender;
    private String address;
    private Long phoneNumber;
    private String email;
    private String marritalStatus;
    private String prefferedLanguage;
    private Date dateCreated;
    private Date dateModified;

}
