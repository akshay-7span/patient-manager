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
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class TreatmentServiceImpl implements TreatmentService{

    @Autowired
    TreatmentRepository treatmentRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    MessageSource messageSource;

    private Mapper mapper= Mappers.getMapper(Mapper.class);

    //Get all the data from treatment table
    public List<TreatmentResponse> getAllTreatment() throws PMRecordNotExistsException {

        List<TreatmentResponse> treatmentDTO=treatmentRepository
                .findAll()
                .stream()
                .map(mapper::mapTreatmentEntityToTreatmentResponse)
                .collect(Collectors.toList());
        if(!treatmentDTO.isEmpty()) {
            return treatmentDTO;
        }else{
            throw new PMRecordNotExistsException(messageSource.getMessage("record.notfound", new Object[]{"not"}, Locale.US));
        }
    }

    //Get the data from treatment table by patientId
    public List<TreatmentResponse> getTreatmentByPatientId(Long patientId) throws PMRecordNotExistsException {
        List<TreatmentResponse> treatmentDTO=treatmentRepository
                .findByPatientId(patientId)
                .stream()
                .map(mapper::mapTreatmentEntityToTreatmentResponse)
                .collect(Collectors.toList());
        if(!treatmentDTO.isEmpty()){
            return treatmentDTO;
        }
        else{
            throw new PMRecordNotExistsException(messageSource.getMessage("record.notfound", new Object[]{"not"}, Locale.US));
        }
    }

    //save data in treatment table
    public TreatmentResponse createTreatment(TreatmentRequest treatmentDTO) throws PMRecordNotExistsException {

        if(patientRepository.existsById(treatmentDTO.getPatientId())) {
            return mapper.mapTreatmentEntityToTreatmentResponse(
                    treatmentRepository
                            .save(mapper.mapTreatmentRequestToTreatmentEntity(treatmentDTO)));
        }else{
            throw new PMRecordNotExistsException(messageSource.getMessage("record.notfound", new Object[]{"not"}, Locale.US));
        }
    }

    //update data in treatment table
    public TreatmentResponse updatetreatment(TreatmentRequest treatmentDTO) throws PMRecordNotExistsException {

        if(treatmentRepository.existsById(treatmentDTO.getId())) {
            return mapper.mapTreatmentEntityToTreatmentResponse(
                    treatmentRepository
                            .save(mapper.mapTreatmentRequestToTreatmentEntity(treatmentDTO)));
        }else{
            throw new PMRecordNotExistsException(messageSource.getMessage("record.notfound", new Object[]{"not"}, Locale.US));
        }
    }
}
