package com.sevenspan.patient.dto.responsedto;

import lombok.Data;

import java.sql.Date;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreatmentResponse {

    private String disease;
    private String symptoms;
    private String duration;
    private Date startDate;
    private Date endDate;
    private String medicine;
    private Boolean resolve;
    private String xId;
}
