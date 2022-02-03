package com.sevenspan.patient.repository;

import com.sevenspan.patient.entity.TreatmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<TreatmentEntity,String> {

//    @Modifying
//    @Query("delete from treatment t where t.patient_id = ?1")
//    void deleteTreatmentByPatientId(int patientId);

    List<TreatmentEntity> findByPatientId(String patientId);
}
