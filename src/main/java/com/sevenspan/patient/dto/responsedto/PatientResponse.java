package com.sevenspan.patient.dto.responsedto;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientResponse {

    private String doctorXid;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Integer age;
    private String gender;
    private String address;
    private Long phoneNumber;
    private String email;
    private String xid;
    private Boolean isDeleted;
    private String status;
    private List<TreatmentResponse> treatmentResponse;
}
