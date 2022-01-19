package com.sevenspan.patient.service.treatmentservice;

import com.sevenspan.patient.dto.requestdto.treatmentdto.TreatmentRequestDTO;
import com.sevenspan.patient.entitydtomapper.EntityDTOMapper;
import com.sevenspan.patient.entity.treatmententity.TreatmentEntity;
import com.sevenspan.patient.exceptions.tmexceptions.TMRecordNotExistsException;
import com.sevenspan.patient.repository.treatmentrepository.TreatmentRepository;
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

    //Get all the data from treatment table
    @SneakyThrows(TMRecordNotExistsException.class)
    public List<TreatmentRequestDTO> getAllTreatment(){

        List<TreatmentRequestDTO> treatmentDTO=treatmentRepository
                .findAll()
                .stream()
                .map(this::convertTreatmentEntitytoTreatmentDTO)
                .collect(Collectors.toList());
        if(!treatmentDTO.isEmpty()) {
            return treatmentDTO;
        }else{
            throw new TMRecordNotExistsException("No any records available");
        }
    }

    //Get the data from treatment table by patientId
    @SneakyThrows(TMRecordNotExistsException.class)
    public List<TreatmentRequestDTO> getTreatmentByPatientId(Long patientId){
        List<TreatmentRequestDTO> treatmentDTO=treatmentRepository
                .findByPatientId(patientId)
                .stream()
                .map(this::convertTreatmentEntitytoTreatmentDTO)
                .collect(Collectors.toList());
        if(!treatmentDTO.isEmpty()){
            return treatmentDTO;
        }
        else{
            throw new TMRecordNotExistsException("No any records available");
        }
    }

    //save data in treatment table
    public TreatmentRequestDTO createTreatment(TreatmentRequestDTO treatmentDTO){

        return convertTreatmentEntitytoTreatmentDTO(
                treatmentRepository
                        .save(convertTreatmentDTOtoTreatmentEntity(treatmentDTO)));
    }

    //update data in treatment table
    public TreatmentRequestDTO updatetreatment(TreatmentRequestDTO treatmentDTO){

        return convertTreatmentEntitytoTreatmentDTO(
                treatmentRepository
                        .save(convertTreatmentDTOtoTreatmentEntity(treatmentDTO)));
    }

    //Transfer data from Entity to DTO
    private TreatmentRequestDTO convertTreatmentEntitytoTreatmentDTO(TreatmentEntity treatmentEntity){

        //This will transfer all the fields values of one data class to another data class
        return EntityDTOMapper.convert(treatmentEntity, TreatmentRequestDTO.class);
    }

    //Transfer data from DTO to Entity
    private TreatmentEntity convertTreatmentDTOtoTreatmentEntity(TreatmentRequestDTO treatmentDTO){

        //This will transfer all the fields values of one data class to another data class
        return EntityDTOMapper.convert(treatmentDTO,TreatmentEntity.class);
    }
}
