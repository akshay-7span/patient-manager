package com.sevenspan.patient.service;

import com.sevenspan.patient.dto.requestdto.TreatmentRequest;
import com.sevenspan.patient.dto.responsedto.TreatmentResponse;

import java.util.List;

public interface TreatmentService {

    public List<TreatmentResponse> getAllTreatment();

    public List<TreatmentResponse> getTreatmentByPatientId(Long patientId);

    public TreatmentResponse createTreatment(TreatmentRequest treatmentDTO);

    public TreatmentResponse updatetreatment(TreatmentRequest treatmentDTO);
}
