package com.sevenspan.patient.dto.responsedto.patientresponsedto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sevenspan.patient.dto.responsedto.treatmentresponsedto.TreatmentResponseDTO;
import com.sevenspan.patient.entity.treatmententity.TreatmentEntity;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientResponseDTO {

    private Long patientId;
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
    private List<TreatmentResponseDTO> treatmentResponseDTO;
}
