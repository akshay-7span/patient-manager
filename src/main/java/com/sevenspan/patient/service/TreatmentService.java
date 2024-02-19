package com.sevenspan.patient.service;

import com.sevenspan.patient.dto.requestdto.TreatmentRequest;
import com.sevenspan.patient.dto.responsedto.TreatmentResponse;
import com.sevenspan.patient.exceptions.PMRecordNotExistsException;

import java.util.List;

public interface TreatmentService {

    public List<TreatmentResponse> getAllTreatment() throws PMRecordNotExistsException;

    public List<TreatmentResponse> getTreatmentByPatientId(Long patientId) throws PMRecordNotExistsException;

    public TreatmentResponse createTreatment(TreatmentRequest treatmentDTO) throws PMRecordNotExistsException;

    public TreatmentResponse updatetreatment(TreatmentRequest treatmentDTO) throws PMRecordNotExistsException;
}
