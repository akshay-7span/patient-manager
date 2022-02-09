package com.sevenspan.patient.repository.custom;

import com.sevenspan.patient.dto.requestdto.patientdto.PatientFilterRequest;
import com.sevenspan.patient.entity.PatientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientRepositoryCustomImpl implements PatientRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<PatientEntity> findPatientWithGivenFilter(PatientFilterRequest patientFilterDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PatientEntity> cq = cb.createQuery(PatientEntity.class);

        Root<PatientEntity> patientEntityRoot = cq.from(PatientEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        if (patientFilterDTO.getDoctorId() != null) {
            predicates.add(cb.equal(patientEntityRoot.get("doctorId"), patientFilterDTO.getDoctorId()));
        }
        if (patientFilterDTO.getFirstName() != null) {
            predicates.add(cb.equal(patientEntityRoot.get("firstName"), patientFilterDTO.getFirstName()));
        }
        if (patientFilterDTO.getAge() != null) {
            predicates.add(cb.equal(patientEntityRoot.get("age"), patientFilterDTO.getAge()));
        }
        if (patientFilterDTO.getGender() != null) {
            predicates.add(cb.equal(patientEntityRoot.get("gender"), patientFilterDTO.getGender()));
        }
        if (patientFilterDTO.getPhoneNumber() != null) {
            predicates.add(cb.equal(patientEntityRoot.get("phoneNumber"), patientFilterDTO.getPhoneNumber()));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<PatientEntity> findByEmailAddress(String email) {
        Query query=entityManager.createNamedQuery("findByEmailAddress");
        query.setParameter(1,email);
        return query.getResultList();
    }

    @Override
    public List<PatientEntity> findByAgeLessThan(Integer age) {
        Query query=entityManager.createNamedQuery("findByAge");
        query.setParameter(1,age);
        return query.getResultList();
    }

}
