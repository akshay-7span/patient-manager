package com.sevenspan.patient.controller;

import com.sevenspan.patient.dto.requestdto.patientdto.PatientRequest;
import com.sevenspan.patient.dto.requestdto.patientdto.PatientFilterRequest;
import com.sevenspan.patient.dto.responsedto.SuccessMessage;
import com.sevenspan.patient.dto.responsedto.PatientResponse;
import com.sevenspan.patient.service.PatientService;
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
    public SuccessMessage<List<PatientResponse>> getAllPatients() {

        log.info("Enter into PatientController.getPatient() method");
        return new SuccessMessage<>(patientService.getAllPatients());
    }

    @GetMapping(value = "/{id}")
    public SuccessMessage<PatientResponse> getPatientById(@PathVariable("id") Long id) {

        log.info("Enter into PatientController.getPatientById() method");
        return new SuccessMessage<>(patientService.getPatientById(id));
    }

    @GetMapping(value = "/filter-phone-email")
    public SuccessMessage<List<PatientResponse>> getPatientByPhoneNumberAndEmail(
            @RequestParam("phoneNumber") Long phoneNumber
            ,@RequestParam("email") String email
    ) {
        log.info("Enter into PatientController.getPatientByPhoneNumberAndEmail() method");
        return new SuccessMessage<>(patientService.getPatientByPhoneNumberAndEmail(phoneNumber, email));
    }

    @GetMapping(value = "/filter-email-end")
    public SuccessMessage<List<PatientResponse>> getPatientByEmailEndsWith(@RequestParam("emailEnd") String emailEnd) {
        log.info("Enter into PatientController.getPatientByEmailEndsWith() method");
        return new SuccessMessage<>(patientService.getPatientByEmailEndsWith(emailEnd));
    }

    @GetMapping(value="/expose")
    public SuccessMessage<List<PatientResponse>> getPatientByDoctorId(
            @RequestParam("id") Long id
            ,@RequestParam("pageNumber") Integer pageNumber
            ,@RequestParam("pageSize") Integer pageSize
            ,@RequestParam("sortBy") String sortBy
    ){

        log.info("Enter into PatientController.getPatientByDoctorId() method");
        return new SuccessMessage<>(patientService.getPatientByDoctorId(id,pageNumber,pageSize,sortBy));
    }

    @GetMapping(value = "/filter/")
    public SuccessMessage<List<PatientResponse>> getPatientByGivenFilter(@RequestBody PatientFilterRequest patientFilterDTO) {

        log.info("Enter into PatientController.getPatient() method");
        return new SuccessMessage<>(patientService.getPatientByGivenFilter(patientFilterDTO));
    }

    @GetMapping(value = "/filter-email")
    public SuccessMessage<List<PatientResponse>> getPatientByEmailAddress(@RequestParam("email") String email) {
        log.info("Enter into PatientController.getPatientByEmailAddress() method");
        return new SuccessMessage<>(patientService.getPatientByEmailAddress(email));
    }

    @GetMapping(value = "/filter-age")
    public SuccessMessage<List<PatientResponse>> getPatientByAgeLessThan(@RequestParam("age") Integer age) {
        log.info("Enter into PatientController.getPatientByAge() method");
        return new SuccessMessage<>(patientService.getPatientByAgeLessThan(age));
    }

    @PostMapping(value = "/")
    public SuccessMessage<PatientResponse> createPatient(@RequestBody PatientRequest patientRequestDTO) {
        log.info("Enter into PatientController.createPatient() method");
        return new SuccessMessage<>(patientService.createPatient(patientRequestDTO));
    }

    @PutMapping(value = "/")
    public SuccessMessage<PatientResponse> updatePatient(@RequestBody PatientRequest patientRequestDTO) {
        log.info("Enter into PatientController.updatePatient() method");
        return new SuccessMessage<>(patientService.updatePatient(patientRequestDTO));
    }

    @DeleteMapping(value = "/{id}")
    public SuccessMessage<String> deletePatient(@PathVariable("id") Long id) {
        log.info("Enter into PatientController.deletePatient() method");
        patientService.deletePatient(id);
        return new SuccessMessage("Data deleted successfully");
    }
}
