package com.sevenspan.patient.service;

import com.sevenspan.patient.dto.requestdto.patientdto.PatientFilterRequest;
import com.sevenspan.patient.dto.requestdto.patientdto.PatientRequest;
import com.sevenspan.patient.dto.responsedto.PatientResponse;
import com.sevenspan.patient.entity.PatientEntity;
import com.sevenspan.patient.enums.UserStatus;
import com.sevenspan.patient.exceptions.PMRecordExistsException;
import com.sevenspan.patient.exceptions.PMRecordNotExistsException;
import com.sevenspan.patient.mapper.Mapper;
import com.sevenspan.patient.repository.PatientRepository;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PatientServiceImpl implements PatientService{

    private static final String EXCEPTIONMESSAGE="No any records available";

    private Mapper mapper= Mappers.getMapper(Mapper.class);

    @Autowired
    PatientRepository patientRepository;

    //Get all the data from patient table
    @Override
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getAllPatients() {

        List<PatientResponse> patientDTO = patientRepository
                .findAll()
                .stream()
                .map(mapper::getPatientResponse)
                .collect(Collectors.toList());

        if(!patientDTO.isEmpty()) {
            return patientDTO;
        }else{
            throw new PMRecordNotExistsException(EXCEPTIONMESSAGE);
        }
    }

    //Get the data from patient table by id
    @Override
    @SneakyThrows(PMRecordNotExistsException.class)
    public PatientResponse getPatientById(Long patientId){

        if(patientRepository.existsById(patientId)) {
            return patientRepository
                    .findById(patientId)
                    .map(mapper::getPatientResponse)
                    .get();
        }else{
            throw new PMRecordNotExistsException(EXCEPTIONMESSAGE);
        }
    }

    //Get the data from patient table by doctorId
    @Override
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getPatientByDoctorId(Long doctorId, Integer pageNumber, Integer pageSize, String sortBy){

        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<PatientEntity> patientEntityPage=patientRepository
                .findByDoctorId(doctorId,pageable);

        if(patientEntityPage.hasContent()) {
            return patientEntityPage
                    .getContent()
                    .stream()
                    .map(mapper::getPatientResponse)
                    .collect(Collectors.toList());
        }else{
            throw new PMRecordNotExistsException(EXCEPTIONMESSAGE);
        }
    }

    //Get the data from patient table by given filter
    @Override
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getPatientByGivenFilter(PatientFilterRequest patientFilterDTO){

        List<PatientResponse> patientResponseDTO = patientRepository
                .findPatientWithGivenFilter(patientFilterDTO)
                .stream()
                .map(mapper::getPatientResponse)
                .collect(Collectors.toList());
        if(!patientResponseDTO.isEmpty()){
            return patientResponseDTO;
        }else{
            throw new PMRecordNotExistsException(EXCEPTIONMESSAGE);
        }
    }

    //Get the data from patient table by phoneNumber and email
    @Override
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getPatientByPhoneNumberAndEmail(Long phoneNumber, String email){

        if(patientRepository.existsByPhoneNumberAndEmail(phoneNumber,email)) {
            return patientRepository
                    .findByPhoneNumberAndEmail(phoneNumber,email)
                    .stream()
                    .map(mapper::getPatientResponse)
                    .collect(Collectors.toList());
        }else{
            throw new PMRecordNotExistsException(EXCEPTIONMESSAGE);
        }
    }

    //Get the data from patient table by email ends with
    @Override
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getPatientByEmailEndsWith(String emailEnd){

        List<PatientResponse> patientResponseDTO = patientRepository
                .findByEmailEndWith(emailEnd)
                .stream()
                .map(mapper::getPatientResponse)
                .collect(Collectors.toList());
        if(!patientResponseDTO.isEmpty()){
            return patientResponseDTO;
        }else{
            throw new PMRecordNotExistsException(EXCEPTIONMESSAGE);
        }
    }

    //Get the data from patient table by email address
    @Override
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getPatientByEmailAddress(String email){

        List<PatientResponse> patientResponseDTO = patientRepository
                .findByEmailAddress(email)
                .stream()
                .map(mapper::getPatientResponse)
                .collect(Collectors.toList());
        if(!patientResponseDTO.isEmpty()){
            return patientResponseDTO;
        }else{
            throw new PMRecordNotExistsException(EXCEPTIONMESSAGE);
        }
    }

    //Get the data from patient table by age
    @Override
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getPatientByAgeLessThan(Integer age){

        List<PatientResponse> patientResponseDTO = patientRepository
                .findByAgeLessThan(age)
                .stream()
                .map(mapper::getPatientResponse)
                .collect(Collectors.toList());
        if(!patientResponseDTO.isEmpty()){
            return patientResponseDTO;
        }else{
            throw new PMRecordNotExistsException(EXCEPTIONMESSAGE);
        }
    }

    //save data in patient table
    @Override
    @SneakyThrows(PMRecordExistsException.class)
    public PatientResponse createPatient(PatientRequest patientRequestDTO) {

        if(!patientRepository.existsByPhoneNumber(patientRequestDTO.getPhoneNumber())) {
            return mapper.getPatientResponse(
                    patientRepository
                            .save(mapper.getPatientEntity(patientRequestDTO)));
        }else{
            throw new PMRecordExistsException("Patient already exists");
        }
    }

    //update data in patient table
    @Override
    @SneakyThrows()
    public PatientResponse updatePatient(PatientRequest patientRequestDTO){

        if(patientRepository.existsByPhoneNumber(patientRequestDTO.getPhoneNumber())) {
            return mapper.getPatientResponse(
                    patientRepository
                            .save(mapper.getPatientEntity(patientRequestDTO)));
        }else{
            throw new PMRecordNotExistsException("Patient not exists to update");
        }
    }

    //delete data from patient table
    @Override
    public void deletePatient(Long patientId) {

        patientRepository.deleteById(patientId);
    }

    //Update patient status
    @Override
    @SneakyThrows
    public void updateStatusRequestInactive(String xId) {

        if(patientRepository.existsByxId(xId)){
            patientRepository.updateStatusRequestInactive(xId);
        }
        else{
            throw new PMRecordNotExistsException("Patient not exists to update");
        }

    }

    @Override
    public String updateStatusInactive() {

        List<PatientEntity> patientEntityList = patientRepository.findByStatusIs(UserStatus.INACTIVATION_REQUESTED.name());
        if(!patientEntityList.isEmpty()){
            patientEntityList.forEach(patientEntity -> patientEntity.setStatus(UserStatus.INACTIVE.name()));
            patientRepository.saveAll(patientEntityList);
            return "Statuses are updated";
        }else{
            return "Statuses are UpToDate";
        }
    }


}
