package com.sevenspan.patient.dto.requestdto;

import lombok.Data;

import java.sql.Date;

@Data
public class TreatmentRequest {

    private Long id;
    private Long patientId;
    private String disease;
    private String symptoms;
    private String duration;
    private Date startDate;
    private Date endDate;
    private String medicine;
    private Boolean resolve;
    private String xId;

}
