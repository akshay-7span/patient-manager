package com.sevenspan.patient.dto.requestdto.patientdto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientRequest {

    private String doctorXid;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private Long phoneNumber;
    private String email;
    private String xid;

}
