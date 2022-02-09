package com.sevenspan.patient.repository;

import com.sevenspan.patient.entity.TreatmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<TreatmentEntity,Long> {
    List<TreatmentEntity> findByPatientId(Long patientId);
}
