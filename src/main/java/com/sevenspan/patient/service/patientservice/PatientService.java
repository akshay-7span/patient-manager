package com.sevenspan.patient.service.patientservice;

import com.sevenspan.patient.dto.requestdto.patientdto.PatientRequestDTO;
import com.sevenspan.patient.dto.responsedto.patientresponsedto.PatientResponseDTO;
import com.sevenspan.patient.entitydtomapper.EntityDTOMapper;
import com.sevenspan.patient.entity.patiententity.PatientEntity;
import com.sevenspan.patient.exceptions.pmexceptions.PMRecordExistsException;
import com.sevenspan.patient.exceptions.pmexceptions.PMRecordNotExistsException;
import com.sevenspan.patient.repository.patientrepository.PatientRepository;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;//beanUtils//apachecommons
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class PatientService {

    private static final ModelMapper modelMapper=new ModelMapper();

    @Autowired
    PatientRepository patientRepository;

    //Get all the data from patient table
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<PatientResponseDTO> getAllPatients() {
            log.info("Enter into PatientService.getAllPatients() method");
            List<PatientResponseDTO> patientDTO = patientRepository
                    .findAll()
                    .stream()
                    .map(this::convertPatientEntitytoPatientResponseDTO)
                    .collect(Collectors.toList());

            if(!patientDTO.isEmpty()) {
                return patientDTO;
            }else{
                throw new PMRecordNotExistsException("No any records available");
            }
    }

    //Get the data from patient table by id
    public PatientResponseDTO getPatientById(Long patientId){

        log.info("Enter into PatientService.getPatientById() method");
        PatientResponseDTO patientResponseDTO=patientRepository
                .findById(patientId)
                .map(this::convertPatientEntitytoPatientResponseDTO)
                .get();
        return patientResponseDTO;
    }

    //save data in patient table
    @SneakyThrows(PMRecordExistsException.class)
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {

        log.info("Enter into PatientService.createPatient() method");
        if(patientRepository.findByPhoneNumber(patientRequestDTO.getPhoneNumber()).isEmpty()) {
            return convertPatientEntitytoPatientResponseDTO(
                    patientRepository
                            .save(convertPatientRequestDTOtoPatientEntity(patientRequestDTO)));
        }else{
            throw new PMRecordExistsException("Patient already exists");
        }
    }

    //update data in patient table
    @SneakyThrows()
    public PatientResponseDTO updatePatient(PatientRequestDTO patientRequestDTO){

        log.info("Enter into PatientService.updatePatient() method");
        if(!patientRepository.findByPhoneNumber(patientRequestDTO.getPhoneNumber()).isEmpty()) {
            return convertPatientEntitytoPatientResponseDTO(
                    patientRepository
                            .save(convertPatientRequestDTOtoPatientEntity(patientRequestDTO)));
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
    private PatientEntity convertPatientRequestDTOtoPatientEntity(PatientRequestDTO patientRequestDTO){

        //This will transfer all the fields values of one data class to another data class
        return EntityDTOMapper.convert(patientRequestDTO,PatientEntity.class);
    }

    private PatientResponseDTO convertPatientEntitytoPatientResponseDTO(PatientEntity patientEntity){

        //PatientResponseDTO patientResponseDTO=modelMapper.map(patientEntity, PatientResponseDTO.class);
        return EntityDTOMapper.convert(patientEntity,PatientResponseDTO.class);
    }
}
