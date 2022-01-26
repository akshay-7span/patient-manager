package com.sevenspan.patient.controller.patientcontroller;

import com.sevenspan.patient.dto.requestdto.patientdto.PatientRequestDTO;
import com.sevenspan.patient.dto.responsedto.messagedto.SuccessMessageDTO;
import com.sevenspan.patient.dto.responsedto.patientresponsedto.PatientResponseDTO;
import com.sevenspan.patient.service.patientservice.PatientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@Log4j2
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping(value = "/")
    public SuccessMessageDTO<List<PatientResponseDTO>> getAllPatients() {

        log.info("Enter into PatientController.getPatient() method");
        return new SuccessMessageDTO<List<PatientResponseDTO>>(patientService.getAllPatients());
    }

    @GetMapping(value = "/{id}")
    public SuccessMessageDTO<PatientResponseDTO> getPatientById(@PathVariable("id") Long id) {

        log.info("Enter into PatientController.getPatientById() method");
        return new SuccessMessageDTO<>(patientService.getPatientById(id));
    }

    @GetMapping(value="/expose/{id}")
    public SuccessMessageDTO<List<PatientResponseDTO>> getPatientByDoctorId(@PathVariable("id") Long id){

        log.info("Enter into PatientController.getPatientByDoctorId() method");
        return new SuccessMessageDTO<List<PatientResponseDTO>>(patientService.getPatientByDoctorId(id));
    }

    @PostMapping(value = "/")
    public SuccessMessageDTO<PatientResponseDTO> createPatient(@RequestBody PatientRequestDTO patientRequestDTO) {
        log.info("Enter into PatientController.getPatientById() method");
        return new SuccessMessageDTO<PatientResponseDTO>(patientService.createPatient(patientRequestDTO));
    }

    @PutMapping(value = "/")
    public SuccessMessageDTO<PatientResponseDTO> updatePatient(@RequestBody PatientRequestDTO patientRequestDTO) {
        log.info("Enter into PatientController.getPatientById() method");
        return new SuccessMessageDTO<>(patientService.updatePatient(patientRequestDTO));
    }

    @DeleteMapping(value = "/{id}")
    public SuccessMessageDTO deletePatient(@PathVariable("id") Long id) {
        log.info("Enter into PatientController.getPatientById() method");
        patientService.deletePatient(id);
        return new SuccessMessageDTO("Data deleted successfully");
    }
}
