package com.sevenspan.patient.repository;

import com.sevenspan.patient.repository.custom.PatientRepositoryCustom;
import com.sevenspan.patient.entity.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//Here PatientRepositoryCustom for criteria builder and criteria queries
public interface PatientRepository extends JpaRepository<PatientEntity, String>, PatientRepositoryCustom {

    //For query by method name with "And" condition
    List<PatientEntity> findByPhoneNumberAndEmail(Long phoneNumber,String email);

    //For get result in page
    Page<PatientEntity> findByDoctorId(Long doctorId, Pageable pageable);

    //For check record availability(Query by method name)
    Boolean existsByPhoneNumber(Long phoneNumber);

    //For check record availability with "And" condition
    Boolean existsByPhoneNumberAndEmail(Long phoneNumber,String email);

    //For original query
    @Query("from patient p where p.email like %?1")
    List<PatientEntity> findByEmailEndWith(String emailEnd);
}
