package com.sevenspan.patient.repository;

import com.sevenspan.patient.repository.custom.PatientRepositoryCustom;
import com.sevenspan.patient.entity.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//Here PatientRepositoryCustom for criteria builder and criteria queries
public interface PatientRepository extends JpaRepository<PatientEntity, Long>, PatientRepositoryCustom {

    //For query by method name with "And" condition
    List<PatientEntity> findByPhoneNumberAndEmail(Long phoneNumber,String email);

    //For get result in page
    Page<PatientEntity> findByDoctorXid(String doctorXid, Pageable pageable);

    PatientEntity findByXid(String xid);

    //For check record availability(Query by method name)
    Boolean existsByPhoneNumber(Long phoneNumber);

    //For check record availability with "And" condition
    Boolean existsByPhoneNumberAndEmail(Long phoneNumber,String email);

    Boolean existsByXid(String xid);

    //For original query
    @Query("from patient p where p.email like %?1")
    List<PatientEntity> findByEmailEndWith(String emailEnd);

    @Modifying
    @Query("update patient set status = 'INACTIVATION_REQUESTED' where x_id = :xId")
    void updateStatusRequestInactive(@Param("xId") String xId);

    List<PatientEntity> findByStatusIs(String status);

    @Modifying
    @Query("update patient set status = 'INACTIVE' where x_id = :xId")
    void updateStatusInactive(@Param("xId") String xId);

    @Modifying
    @Query("update patient set is_deleted = true where x_id = :xid")
    void updateStatusIsdeleted(@Param("xid") String xid);

}
