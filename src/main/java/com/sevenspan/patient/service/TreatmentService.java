package com.sevenspan.patient.service;

import com.sevenspan.patient.dto.requestdto.TreatmentRequest;
import com.sevenspan.patient.mapper.Mapper;
import com.sevenspan.patient.entity.TreatmentEntity;
import com.sevenspan.patient.exceptions.PMRecordNotExistsException;
import com.sevenspan.patient.repository.PatientRepository;
import com.sevenspan.patient.repository.TreatmentRepository;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreatmentService {

    private static final ModelMapper modelMapper=new ModelMapper();

    @Autowired
    TreatmentRepository treatmentRepository;

    @Autowired
    PatientRepository patientRepository;

    //Get all the data from treatment table
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<TreatmentRequest> getAllTreatment(){

        List<TreatmentRequest> treatmentDTO=treatmentRepository
                .findAll()
                .stream()
                .map(this::convertTreatmentEntitytoTreatmentDTO)
                .collect(Collectors.toList());
        if(!treatmentDTO.isEmpty()) {
            return treatmentDTO;
        }else{
            throw new PMRecordNotExistsException("No any records available");
        }
    }

    //Get the data from treatment table by patientId
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<TreatmentRequest> getTreatmentByPatientId(String patientId){
        List<TreatmentRequest> treatmentDTO=treatmentRepository
                .findByPatientId(patientId)
                .stream()
                .map(this::convertTreatmentEntitytoTreatmentDTO)
                .collect(Collectors.toList());
        if(!treatmentDTO.isEmpty()){
            return treatmentDTO;
        }
        else{
            throw new PMRecordNotExistsException("No any records available");
        }
    }

    //save data in treatment table
    @SneakyThrows(PMRecordNotExistsException.class)
    public TreatmentRequest createTreatment(TreatmentRequest treatmentDTO){

        if(patientRepository.existsById(treatmentDTO.getPatientId())) {
            return convertTreatmentEntitytoTreatmentDTO(
                    treatmentRepository
                            .save(convertTreatmentDTOtoTreatmentEntity(treatmentDTO)));
        }else{
            throw new PMRecordNotExistsException("No any patients available");
        }
    }

    //update data in treatment table
    @SneakyThrows(PMRecordNotExistsException.class)
    public TreatmentRequest updatetreatment(TreatmentRequest treatmentDTO){

        if(treatmentRepository.existsById(treatmentDTO.getTreatmentId())) {
            return convertTreatmentEntitytoTreatmentDTO(
                    treatmentRepository
                            .save(convertTreatmentDTOtoTreatmentEntity(treatmentDTO)));
        }else{
            throw new PMRecordNotExistsException("No any records available to update");
        }
    }

    //Transfer data from Entity to DTO
    private TreatmentRequest convertTreatmentEntitytoTreatmentDTO(TreatmentEntity treatmentEntity){

        //This will transfer all the fields values of one data class to another data class
        return Mapper.convert(treatmentEntity, TreatmentRequest.class);
    }

    //Transfer data from DTO to Entity
    private TreatmentEntity convertTreatmentDTOtoTreatmentEntity(TreatmentRequest treatmentDTO){

        //This will transfer all the fields values of one data class to another data class
        return Mapper.convert(treatmentDTO,TreatmentEntity.class);
    }
}
