package com.sevenspan.patient.repository.custom;

import com.sevenspan.patient.dto.requestdto.patientdto.PatientFilterRequest;
import com.sevenspan.patient.entity.PatientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
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

        if (patientFilterDTO.getDoctorXid() != null) {
            predicates.add(cb.equal(patientEntityRoot.get("doctorXid"), patientFilterDTO.getDoctorXid()));
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

    @Override
    public PatientEntity findByPatientXidLazy(String xid) {

        Query q = entityManager.createQuery("SELECT p FROM patient p JOIN FETCH p.treatmentEntity t WHERE p.xid = :xid");
        q.setParameter("xid", xid);
        return (PatientEntity) q.getSingleResult();

//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery q = cb.createQuery(PatientEntity.class);
//        Root root = q.from(PatientEntity.class);
//        root.fetch("treatmentEntity", JoinType.INNER);
//        q.select(root);
//        q.where(cb.equal(root.get("xid"), xid));
//
//        return (PatientEntity) entityManager.createQuery(q).getSingleResult();
    }

}
