package com.sevenspan.patient.repository.patientrepository;

import com.sevenspan.patient.dto.responsedto.patientresponsedto.PatientResponseDTO;
import com.sevenspan.patient.entity.patiententity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity,Long> {

    List<PatientEntity> findByPhoneNumber(Long phoneNumber);
}
