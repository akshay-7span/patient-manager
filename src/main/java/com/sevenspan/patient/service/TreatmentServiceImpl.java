package com.sevenspan.patient.service;

import com.sevenspan.patient.dto.requestdto.TreatmentRequest;
import com.sevenspan.patient.dto.responsedto.TreatmentResponse;
import com.sevenspan.patient.exceptions.PMRecordNotExistsException;
import com.sevenspan.patient.mapper.Mapper;
import com.sevenspan.patient.repository.PatientRepository;
import com.sevenspan.patient.repository.TreatmentRepository;
import lombok.SneakyThrows;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreatmentServiceImpl implements TreatmentService{

    @Autowired
    TreatmentRepository treatmentRepository;

    @Autowired
    PatientRepository patientRepository;

    private Mapper mapper= Mappers.getMapper(Mapper.class);

    //Get all the data from treatment table
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<TreatmentResponse> getAllTreatment(){

        List<TreatmentResponse> treatmentDTO=treatmentRepository
                .findAll()
                .stream()
                .map(mapper::getTreatmentResponse)
                .collect(Collectors.toList());
        if(!treatmentDTO.isEmpty()) {
            return treatmentDTO;
        }else{
            throw new PMRecordNotExistsException("No any records available");
        }
    }

    //Get the data from treatment table by patientId
    @SneakyThrows(PMRecordNotExistsException.class)
    public List<TreatmentResponse> getTreatmentByPatientId(Long patientId){
        List<TreatmentResponse> treatmentDTO=treatmentRepository
                .findByPatientId(patientId)
                .stream()
                .map(mapper::getTreatmentResponse)
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
    public TreatmentResponse createTreatment(TreatmentRequest treatmentDTO){

        if(patientRepository.existsById(treatmentDTO.getPatientId())) {
            return mapper.getTreatmentResponse(
                    treatmentRepository
                            .save(mapper.getTreatmentEntity(treatmentDTO)));
        }else{
            throw new PMRecordNotExistsException("No any patients available");
        }
    }

    //update data in treatment table
    @SneakyThrows(PMRecordNotExistsException.class)
    public TreatmentResponse updatetreatment(TreatmentRequest treatmentDTO){

        if(treatmentRepository.existsById(treatmentDTO.getId())) {
            return mapper.getTreatmentResponse(
                    treatmentRepository
                            .save(mapper.getTreatmentEntity(treatmentDTO)));
        }else{
            throw new PMRecordNotExistsException("No any records available to update");
        }
    }

    //Transfer data from Entity to DTO
//    private TreatmentRequest convertTreatmentEntitytoTreatmentDTO(TreatmentEntity treatmentEntity){
//
//        //This will transfer all the fields values of one data class to another data class
//        return Mapper.convert(treatmentEntity, TreatmentRequest.class);
//    }
//
//    //Transfer data from DTO to Entity
//    private TreatmentEntity convertTreatmentDTOtoTreatmentEntity(TreatmentRequest treatmentDTO){
//
//        //This will transfer all the fields values of one data class to another data class
//        return Mapper.convert(treatmentDTO,TreatmentEntity.class);
//    }
}