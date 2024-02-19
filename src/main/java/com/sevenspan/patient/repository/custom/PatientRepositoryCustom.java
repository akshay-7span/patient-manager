package com.sevenspan.patient.repository.custom;

import com.sevenspan.patient.dto.requestdto.patientdto.PatientFilterRequest;
import com.sevenspan.patient.entity.PatientEntity;

import java.util.List;

public interface PatientRepositoryCustom {

    List<PatientEntity> findPatientWithGivenFilter(PatientFilterRequest patientFilterRequest);

    List<PatientEntity> findByEmailAddress(String email);

    List<PatientEntity> findByAgeLessThan(Integer age);

    PatientEntity findByPatientXidLazy(String xid);

}
