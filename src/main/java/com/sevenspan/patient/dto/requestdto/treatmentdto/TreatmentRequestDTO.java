package com.sevenspan.patient.dto.requestdto.treatmentdto;

import lombok.Data;

import java.sql.Date;

@Data
public class TreatmentRequestDTO {

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
