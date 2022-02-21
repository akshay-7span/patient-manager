package com.sevenspan.patient.service;

import com.sevenspan.patient.dto.requestdto.patientdto.PatientFilterRequest;
import com.sevenspan.patient.dto.requestdto.patientdto.PatientRequest;
import com.sevenspan.patient.dto.responsedto.PatientResponse;
import com.sevenspan.patient.entity.PatientEntity;
import com.sevenspan.patient.entity.TreatmentEntity;
import com.sevenspan.patient.enums.UserStatus;
import com.sevenspan.patient.exceptions.PMRecordExistsException;
import com.sevenspan.patient.exceptions.PMRecordNotExistsException;
import com.sevenspan.patient.exceptions.PMSchedulerJobFailed;
import com.sevenspan.patient.mapper.Mapper;
import com.sevenspan.patient.repository.PatientRepository;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private Mapper mapper = Mappers.getMapper(Mapper.class);

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    MessageSource messageSource;

//    private final String RECORDEXISTS = messageSource.getMessage("record.exists", null, null);
//    private final String RECORDNOTFOUND = messageSource.getMessage("record.notfound", null, null);
//    private final String SCHEDULERFAIL = messageSource.getMessage("scheduler.patient.status.inactive", null, null);

    //Get all the data from patient table
    @Override
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getAllPatients() {

        List<PatientResponse> patientDTO = patientRepository
                .findAll()
                .stream()
                .map(mapper::mapPatientEntityToPatientResponse)
                .collect(Collectors.toList());

        if (!patientDTO.isEmpty()) {
            return patientDTO;
        } else {
            throw new PMRecordNotExistsException("RECORDNOTFOUND");
        }
    }

    //Get the data from patient table by id
    @Override
    @SneakyThrows(PMRecordNotExistsException.class)
    public PatientResponse getPatientById(String xid) {

        if (patientRepository.existsByXid(xid)) {

            List<TreatmentEntity> treatmentEntity=patientRepository.findByXid(xid).getTreatmentEntity();
            log.info(treatmentEntity.size());

            return mapper.mapPatientEntityToPatientResponse(
                    patientRepository.findByPatientXidLazy(xid));

//            return patientRepository.findByXid(xid);

//            return mapper.mapPatientEntityToPatientResponse(
//                    patientRepository.findByXid(xid));


        } else {
            throw new PMRecordNotExistsException("RECORDNOTFOUND");
        }
    }

    //Get the data from patient table by doctorXid
    @Override
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getPatientByDoctorXid(String doctorXid, Integer pageNumber, Integer pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<PatientEntity> patientEntityPage = patientRepository
                .findByDoctorXid(doctorXid, pageable);

        if (patientEntityPage.hasContent()) {
            return patientEntityPage
                    .getContent()
                    .stream()
                    .map(mapper::mapPatientEntityToPatientResponse)
                    .collect(Collectors.toList());
        } else {
            throw new PMRecordNotExistsException("RECORDNOTFOUND");
        }
    }

    //Get the data from patient table by given filter
    @Override
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getPatientByGivenFilter(PatientFilterRequest patientFilterDTO) {

        List<PatientResponse> patientResponseDTO = patientRepository
                .findPatientWithGivenFilter(patientFilterDTO)
                .stream()
                .map(mapper::mapPatientEntityToPatientResponse)
                .collect(Collectors.toList());
        if (!patientResponseDTO.isEmpty()) {
            return patientResponseDTO;
        } else {
            throw new PMRecordNotExistsException("RECORDNOTFOUND");
        }
    }

    //Get the data from patient table by phoneNumber and email
    @Override
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getPatientByPhoneNumberAndEmail(Long phoneNumber, String email) {

        if (patientRepository.existsByPhoneNumberAndEmail(phoneNumber, email)) {
            return patientRepository
                    .findByPhoneNumberAndEmail(phoneNumber, email)
                    .stream()
                    .map(mapper::mapPatientEntityToPatientResponse)
                    .collect(Collectors.toList());
        } else {
            throw new PMRecordNotExistsException("RECORDNOTFOUND");
        }
    }

    //Get the data from patient table by email ends with
    @Override
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getPatientByEmailEndsWith(String emailEnd) {

        List<PatientResponse> patientResponseDTO = patientRepository
                .findByEmailEndWith(emailEnd)
                .stream()
                .map(mapper::mapPatientEntityToPatientResponse)
                .collect(Collectors.toList());
        if (!patientResponseDTO.isEmpty()) {
            return patientResponseDTO;
        } else {
            throw new PMRecordNotExistsException("RECORDNOTFOUND");
        }
    }

    //Get the data from patient table by email address
    @Override
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getPatientByEmailAddress(String email) {

        List<PatientResponse> patientResponseDTO = patientRepository
                .findByEmailAddress(email)
                .stream()
                .map(mapper::mapPatientEntityToPatientResponse)
                .collect(Collectors.toList());
        if (!patientResponseDTO.isEmpty()) {
            return patientResponseDTO;
        } else {
            throw new PMRecordNotExistsException("RECORDNOTFOUND");
        }
    }

    //Get the data from patient table by age
    @Override
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getPatientByAgeLessThan(Integer age) {

        List<PatientResponse> patientResponseDTO = patientRepository
                .findByAgeLessThan(age)
                .stream()
                .map(mapper::mapPatientEntityToPatientResponse)
                .collect(Collectors.toList());
        if (!patientResponseDTO.isEmpty()) {
            return patientResponseDTO;
        } else {
            throw new PMRecordNotExistsException("RECORDNOTFOUND");
        }
    }

    //save data in patient table
    @Override
    @SneakyThrows(PMRecordExistsException.class)
    public PatientResponse createPatient(PatientRequest patientRequestDTO) {

        if (!patientRepository.existsByPhoneNumber(patientRequestDTO.getPhoneNumber())) {
            return mapper.mapPatientEntityToPatientResponse(
                    patientRepository
                            .save(mapper.mapPatientRequestToPatientEntity(patientRequestDTO)));
        } else {
            throw new PMRecordExistsException("RECORDEXISTS");
        }
    }

    //update data in patient table
    @Override
    @SneakyThrows(PMRecordNotExistsException.class)
    public PatientResponse updatePatient(PatientRequest patientRequestDTO) {

        if (patientRepository.existsByPhoneNumber(patientRequestDTO.getPhoneNumber())) {
            return mapper.mapPatientEntityToPatientResponse(
                    patientRepository
                            .save(mapper.mapPatientRequestToPatientEntity(patientRequestDTO)));
        } else {
            throw new PMRecordNotExistsException("RECORDNOTFOUND");
        }
    }

    //delete data from patient table
    @Override
    public void deletePatient(String xid) {

        patientRepository.updateStatusIsdeleted(xid);
    }

    //Update patient status
    @Override
    @SneakyThrows(PMRecordNotExistsException.class)
    public void updateStatusRequestInactive(String xid) {

        if (patientRepository.existsByXid(xid)) {
            patientRepository.updateStatusRequestInactive(xid);
        } else {
            throw new PMRecordNotExistsException("RECORDNOTFOUND");
        }

    }

    //Schedule job to update status to of patient inactive
    @Override
    @SneakyThrows(PMSchedulerJobFailed.class)
    public void updateStatusInactive() {

        try {
            List<PatientEntity> patientEntityList = patientRepository.findByStatusIs(UserStatus.INACTIVATION_REQUESTED.name());
            if (!patientEntityList.isEmpty()) {
                patientEntityList.forEach(patientEntity -> patientEntity.setStatus(UserStatus.INACTIVE));
                patientRepository.saveAll(patientEntityList);
            }
        } catch (Exception e) {
            throw new PMSchedulerJobFailed("SCHEDULERFAIL");
        }
    }

}
