package com.sevenspan.patient.dto.responsedto;

import lombok.Data;

import javax.persistence.Column;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientResponse {

    private Long id;
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
    private String xId;
    private Boolean isDeleted;
    private String status;
    private List<TreatmentResponse> treatmentResponse;
}
