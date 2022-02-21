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
                .map(mapper::mapTreatmentEntityToTreatmentResponse)
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
                .map(mapper::mapTreatmentEntityToTreatmentResponse)
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
            return mapper.mapTreatmentEntityToTreatmentResponse(
                    treatmentRepository
                            .save(mapper.mapTreatmentRequestToTreatmentEntity(treatmentDTO)));
        }else{
            throw new PMRecordNotExistsException("No any patients available");
        }
    }

    //update data in treatment table
    @SneakyThrows(PMRecordNotExistsException.class)
    public TreatmentResponse updatetreatment(TreatmentRequest treatmentDTO){

        if(treatmentRepository.existsById(treatmentDTO.getId())) {
            return mapper.mapTreatmentEntityToTreatmentResponse(
                    treatmentRepository
                            .save(mapper.mapTreatmentRequestToTreatmentEntity(treatmentDTO)));
        }else{
            throw new PMRecordNotExistsException("No any records available to update");
        }
    }
}
