package com.sevenspan.patient.service;

import com.sevenspan.patient.dto.requestdto.patientdto.PatientFilterRequest;
import com.sevenspan.patient.dto.requestdto.patientdto.PatientRequest;
import com.sevenspan.patient.dto.responsedto.PatientResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PatientService {

    public List<PatientResponse> getAllPatients();

    public PatientResponse getPatientById(Long patientId);

    public List<PatientResponse> getPatientByDoctorId(Long doctorId, Integer pageNumber, Integer pageSize, String sortBy);

    public List<PatientResponse> getPatientByGivenFilter(PatientFilterRequest patientFilterDTO);

    public List<PatientResponse> getPatientByPhoneNumberAndEmail(Long phoneNumber, String email);

    public List<PatientResponse> getPatientByEmailEndsWith(String emailEnd);

    public List<PatientResponse> getPatientByEmailAddress(String email);

    public List<PatientResponse> getPatientByAgeLessThan(Integer age);

    public PatientResponse createPatient(PatientRequest patientRequestDTO);

    public PatientResponse updatePatient(PatientRequest patientRequestDTO);

    public void deletePatient(Long patientId);

    public void updateStatusRequestInactive(String xid);

    public String updateStatusInactive();


}
