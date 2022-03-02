package com.sevenspan.patient.service;

import com.sevenspan.patient.dto.requestdto.patientdto.PatientFilterRequest;
import com.sevenspan.patient.dto.requestdto.patientdto.PatientRequest;
import com.sevenspan.patient.dto.responsedto.patientresponse.PatientResponse;
import com.sevenspan.patient.dto.responsedto.patientresponse.PatientStringResponse;
import com.sevenspan.patient.entity.PatientEntity;
import com.sevenspan.patient.enums.UserStatus;
import com.sevenspan.patient.exceptions.PMProducerException;
import com.sevenspan.patient.exceptions.PMRecordExistsException;
import com.sevenspan.patient.exceptions.PMRecordNotExistsException;
import com.sevenspan.patient.exceptions.PMSchedulerJobFailed;
import com.sevenspan.patient.mapper.Mapper;
import com.sevenspan.patient.producers.PatientProducer;
import com.sevenspan.patient.repository.PatientRepository;
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
import java.util.Locale;
import java.util.Objects;
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

    @Autowired
    PatientProducer patientProducer;

    //Get all the data from patient table
    @Override
    public List<PatientResponse> getAllPatients() throws PMRecordNotExistsException {

        List<PatientResponse> patientDTO = patientRepository
                .findAll()
                .stream()
                .map(mapper::mapPatientEntityToPatientResponse)
                .collect(Collectors.toList());

        if (!patientDTO.isEmpty()) {
            return patientDTO;
        } else {
            throw new PMRecordNotExistsException(messageSource.getMessage("record.notfound", new Object[]{"not"}, Locale.US));
        }
    }

    //Get the data from patient table by id
    @Override
    public PatientResponse getPatientById(String xid) throws PMRecordNotExistsException {
        PatientEntity patientEntity = patientRepository.findByPatientXidLazy(xid);
        if (Objects.isNull(patientEntity)) {
            throw new PMRecordNotExistsException(messageSource.getMessage("record.notfound", new Object[]{"not"}, Locale.US));
        }

        return mapper.mapPatientEntityToPatientResponse(
                patientEntity);
    }

    //Get the data from patient table by doctorXid
    @Override
    public List<PatientResponse> getPatientByDoctorXid(String doctorXid, Integer pageNumber, Integer pageSize, String sortBy) throws PMRecordNotExistsException {

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
            throw new PMRecordNotExistsException(messageSource.getMessage("record.notfound", new Object[]{"not"}, Locale.US));
        }
    }

    //Get the data from patient table by given filter
    @Override
    public List<PatientResponse> getPatientByGivenFilter(PatientFilterRequest patientFilterDTO) throws PMRecordNotExistsException {

        List<PatientResponse> patientResponseDTO = patientRepository
                .findPatientWithGivenFilter(patientFilterDTO)
                .stream()
                .map(mapper::mapPatientEntityToPatientResponse)
                .collect(Collectors.toList());
        if (!patientResponseDTO.isEmpty()) {
            return patientResponseDTO;
        } else {
            throw new PMRecordNotExistsException(messageSource.getMessage("record.notfound", new Object[]{"not"}, Locale.US));
        }
    }

    //Get the data from patient table by phoneNumber and email
    @Override
    public List<PatientResponse> getPatientByPhoneNumberAndEmail(Long phoneNumber, String email) throws PMRecordNotExistsException {

        if (patientRepository.existsByPhoneNumberAndEmail(phoneNumber, email)) {
            return patientRepository
                    .findByPhoneNumberAndEmail(phoneNumber, email)
                    .stream()
                    .map(mapper::mapPatientEntityToPatientResponse)
                    .collect(Collectors.toList());
        } else {
            throw new PMRecordNotExistsException(messageSource.getMessage("record.notfound", new Object[]{"not"}, Locale.US));
        }
    }

    //Get the data from patient table by email ends with
    @Override
    public List<PatientResponse> getPatientByEmailEndsWith(String emailEnd) throws PMRecordNotExistsException {

        List<PatientResponse> patientResponseDTO = patientRepository
                .findByEmailEndWith(emailEnd)
                .stream()
                .map(mapper::mapPatientEntityToPatientResponse)
                .collect(Collectors.toList());
        if (!patientResponseDTO.isEmpty()) {
            return patientResponseDTO;
        } else {
            throw new PMRecordNotExistsException(messageSource.getMessage("record.notfound", new Object[]{"not"}, Locale.US));
        }
    }

    //Get the data from patient table by email address
    @Override
    public List<PatientResponse> getPatientByEmailAddress(String email) throws PMRecordNotExistsException {

        List<PatientResponse> patientResponseDTO = patientRepository
                .findByEmailAddress(email)
                .stream()
                .map(mapper::mapPatientEntityToPatientResponse)
                .collect(Collectors.toList());
        if (!patientResponseDTO.isEmpty()) {
            return patientResponseDTO;
        } else {
            throw new PMRecordNotExistsException(messageSource.getMessage("record.notfound", new Object[]{"not"}, Locale.US));
        }
    }

    //Get the data from patient table by age
    @Override
    public List<PatientResponse> getPatientByAgeLessThan(Integer age) throws PMRecordNotExistsException {

        List<PatientResponse> patientResponseDTO = patientRepository
                .findByAgeLessThan(age)
                .stream()
                .map(mapper::mapPatientEntityToPatientResponse)
                .collect(Collectors.toList());
        if (!patientResponseDTO.isEmpty()) {
            return patientResponseDTO;
        } else {
            throw new PMRecordNotExistsException(messageSource.getMessage("record.notfound", new Object[]{"not"}, Locale.US));
        }
    }

    //save data in patient table
    @Override
    public PatientResponse createPatient(PatientRequest patientRequestDTO) throws PMRecordExistsException, PMProducerException {

        if (!patientRepository.existsByPhoneNumber(patientRequestDTO.getPhoneNumber())) {
            PatientResponse patientResponse = mapper.mapPatientEntityToPatientResponse(
                    patientRepository
                            .save(mapper.mapPatientRequestToPatientEntity(patientRequestDTO)));
            try {
                patientProducer.sendMessageJSON(patientResponse);
            } catch (Exception exception) {
                throw new PMProducerException(messageSource.getMessage("message.send.callback.failed", null, Locale.US));
            }
            return patientResponse;
        } else {
            throw new PMRecordExistsException(messageSource.getMessage("record.exists", new Object[]{"already"}, Locale.US));
        }
    }

    //update data in patient table
    @Override
    public PatientResponse updatePatient(PatientRequest patientRequestDTO) throws PMRecordNotExistsException, PMProducerException {

        if (patientRepository.existsByPhoneNumber(patientRequestDTO.getPhoneNumber())) {
            PatientResponse patientResponse = mapper.mapPatientEntityToPatientResponse(
                    patientRepository
                            .save(mapper.mapPatientRequestToPatientEntity(patientRequestDTO)));
            try {
                patientProducer.sendMessageJSON(patientResponse);
            } catch (Exception exception) {
                exception.printStackTrace();
//                throw new PMProducerException(messageSource.getMessage("message.send.callback.failed", null, Locale.US));
            }
            return patientResponse;
        } else {
            throw new PMRecordNotExistsException(messageSource.getMessage("record.notfound", new Object[]{"not"}, Locale.US));
        }
    }

    //delete data from patient table
    @Override
    public PatientStringResponse deletePatient(String xid) {

        patientRepository.updateStatusIsdeleted(xid);
        PatientStringResponse patientStringResponse = new PatientStringResponse();
        patientStringResponse.setMessage(messageSource.getMessage("record.deleted", null, Locale.US));
        return patientStringResponse;
    }

    //Update patient status
    @Override
    public PatientStringResponse updateStatusRequestInactive(String xid) throws PMRecordNotExistsException {

        if (patientRepository.existsByXid(xid)) {
            patientRepository.updateStatusRequestInactive(xid);
            PatientStringResponse patientStringResponse = new PatientStringResponse();
            patientStringResponse.setMessage(messageSource.getMessage("record.status.updated", null, Locale.US));
            return patientStringResponse;
        } else {
            throw new PMRecordNotExistsException(messageSource.getMessage("record.notfound", new Object[]{"not"}, Locale.US));
        }
    }

    //Schedule job to update status to of patient inactive
    @Override
    public void updateStatusInactive() throws PMSchedulerJobFailed {

        try {
            List<PatientEntity> patientEntityList = patientRepository.findByStatusIs(UserStatus.INACTIVATION_REQUESTED.name());
            if (!patientEntityList.isEmpty()) {
                patientEntityList.forEach(patientEntity -> patientEntity.setStatus(UserStatus.INACTIVE));
                patientRepository.saveAll(patientEntityList);
            }
        } catch (Exception e) {
            throw new PMSchedulerJobFailed(messageSource.getMessage("scheduler.patient.status.inactive", null, Locale.US));
        }
    }

}
