package com.sevenspan.patient.service;

import com.sevenspan.patient.dto.requestdto.patientdto.PatientFilterRequest;
import com.sevenspan.patient.dto.requestdto.patientdto.PatientRequest;
import com.sevenspan.patient.dto.responsedto.PatientResponse;
import com.sevenspan.patient.entity.PatientEntity;
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

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class PatientServiceImpl implements PatientService{

    private static final String EXCEPTIONMESSAGE="No any records available";

    private Mapper mapper= Mappers.getMapper(Mapper.class);

    @Autowired
    PatientRepository patientRepository;

    //Get all the data from patient table
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getAllPatients() {
        log.info("Enter into PatientService.getAllPatients() method");
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
    @SneakyThrows(PMRecordNotExistsException.class)
    public PatientResponse getPatientById(Long patientId){

        log.info("Enter into PatientService.getPatientById() method");
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
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getPatientByDoctorId(Long doctorId, Integer pageNumber, Integer pageSize, String sortBy){

        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));

        log.info("Enter into PatientService.getPatientByDoctorId() method");
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
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getPatientByGivenFilter(PatientFilterRequest patientFilterDTO){
        log.info("Enter into PatientService.getPatientById() method");
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
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getPatientByPhoneNumberAndEmail(Long phoneNumber, String email){

        log.info("Enter into PatientService.getPatientByPhoneNumberAndEmail() method");
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
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getPatientByEmailEndsWith(String emailEnd){

        log.info("Enter into PatientService.getPatientByEmailEndsWith() method");
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
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getPatientByEmailAddress(String email){

        log.info("Enter into PatientService.getPatientByEmailAddress() method");
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
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponse> getPatientByAgeLessThan(Integer age){

        log.info("Enter into PatientService.getPatientByAgeLessThan() method");
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
    @SneakyThrows(PMRecordExistsException.class)
    public PatientResponse createPatient(PatientRequest patientRequestDTO) {

        log.info("Enter into PatientService.createPatient() method");
        if(!patientRepository.existsByPhoneNumber(patientRequestDTO.getPhoneNumber())) {
            return mapper.getPatientResponse(
                    patientRepository
                            .save(mapper.getPatientEntity(patientRequestDTO)));
        }else{
            throw new PMRecordExistsException("Patient already exists");
        }
    }

    //update data in patient table
    @SneakyThrows()
    public PatientResponse updatePatient(PatientRequest patientRequestDTO){

        log.info("Enter into PatientService.updatePatient() method");
        if(patientRepository.existsByPhoneNumber(patientRequestDTO.getPhoneNumber())) {
            return mapper.getPatientResponse(
                    patientRepository
                            .save(mapper.getPatientEntity(patientRequestDTO)));
        }else{
            throw new PMRecordNotExistsException("Patient not exists to update");
        }
    }

    //delete data from patient table
    public void deletePatient(Long patientId) {

        log.info("Enter into PatientService.deletePatient() method");
        patientRepository.deleteById(patientId);
    }

    //Transfer data from DTO to Entity
//    private PatientEntity convertPatientRequestDTOtoPatientEntity(PatientRequest patientRequestDTO){
//
//        //This will transfer all the fields values of one data class to another data class
//        return Mapper.convert(patientRequestDTO,PatientEntity.class);
//    }
//
//    private PatientResponse convertPatientEntitytoPatientResponseDTO(PatientEntity patientEntity){
//
//        return Mapper.convert(patientEntity, PatientResponse.class);
//    }
//
//    private PatientResponse convertPatientEntityToDTOWithTreatment(PatientEntity patientEntity){
//        List<TreatmentResponse> treatmentResponseDTOS= Mapper
//                .mapAll(patientEntity.getTreatmentEntity(), TreatmentResponse.class);
//        PropertyMap<PatientEntity, PatientResponse> propertyMap = new PropertyMap <PatientEntity, PatientResponse>() {
//            protected void configure() {
//                map().setTreatmentResponse(
//                        treatmentResponseDTOS
//                );
//            }
//        };
//        return Mapper.convertWithCondition(patientEntity, PatientResponse.class,propertyMap);
//    }
}
