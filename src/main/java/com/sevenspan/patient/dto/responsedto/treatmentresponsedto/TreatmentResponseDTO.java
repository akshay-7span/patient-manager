package com.sevenspan.patient.dto.responsedto.treatmentresponsedto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Date;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreatmentResponseDTO{

    private Long treatmentId;
    private Long patientId;
    private String disease;
    private String symptoms;
    private String duration;
    private Date startDate;
    private Date endDate;
    private String medicine;
    private Boolean resolve;

}
