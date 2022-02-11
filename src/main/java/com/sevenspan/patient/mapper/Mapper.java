package com.sevenspan.patient.mapper;

import com.sevenspan.patient.dto.requestdto.TreatmentRequest;
import com.sevenspan.patient.dto.requestdto.patientdto.PatientRequest;
import com.sevenspan.patient.dto.responsedto.PatientResponse;
import com.sevenspan.patient.dto.responsedto.TreatmentResponse;
import com.sevenspan.patient.entity.PatientEntity;
import com.sevenspan.patient.entity.TreatmentEntity;
import org.mapstruct.Mapping;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {


    PatientEntity getPatientEntity(PatientRequest patientRequest);

    @Mapping(target = "treatmentResponse", source = "treatmentEntity")
    PatientResponse getPatientResponse(PatientEntity patientEntity);

    List<TreatmentResponse> getTreatmentResponse(List<TreatmentEntity> treatmentEntity);

    TreatmentEntity getTreatmentEntity(TreatmentRequest treatmentRequest);

    TreatmentResponse getTreatmentResponse(TreatmentEntity treatmentEntity);
}