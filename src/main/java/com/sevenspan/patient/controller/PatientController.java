package com.sevenspan.patient.controller;

import com.sevenspan.patient.dto.requestdto.patientdto.PatientRequest;
import com.sevenspan.patient.dto.requestdto.patientdto.PatientFilterRequest;
import com.sevenspan.patient.dto.responsedto.SuccessResponse;
import com.sevenspan.patient.dto.responsedto.PatientResponse;
import com.sevenspan.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping(value = "/")
    public List<PatientResponse> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping(value = "/{id}")
    public SuccessResponse<PatientResponse> getPatientById(@PathVariable("id") Long id) {
        return new SuccessResponse<>(patientService.getPatientById(id));
    }

    @GetMapping(value = "/filter-phone-email")
    public SuccessResponse<List<PatientResponse>> getPatientByPhoneNumberAndEmail(
            @RequestParam("phoneNumber") Long phoneNumber
            ,@RequestParam("email") String email
    ) {
        return new SuccessResponse<>(patientService.getPatientByPhoneNumberAndEmail(phoneNumber, email));
    }

    @GetMapping(value = "/filter-email-end")
    public SuccessResponse<List<PatientResponse>> getPatientByEmailEndsWith(@RequestParam("emailEnd") String emailEnd) {
        return new SuccessResponse<>(patientService.getPatientByEmailEndsWith(emailEnd));
    }

    @GetMapping(value="/expose")
    public SuccessResponse<List<PatientResponse>> getPatientByDoctorId(
            @RequestParam("id") Long id
            ,@RequestParam("pageNumber") Integer pageNumber
            ,@RequestParam("pageSize") Integer pageSize
            ,@RequestParam("sortBy") String sortBy
    ){
        return new SuccessResponse<>(patientService.getPatientByDoctorId(id,pageNumber,pageSize,sortBy));
    }

    @GetMapping(value = "/filter/")
    public SuccessResponse<List<PatientResponse>> getPatientByGivenFilter(@RequestBody PatientFilterRequest patientFilterDTO) {
        return new SuccessResponse<>(patientService.getPatientByGivenFilter(patientFilterDTO));
    }

    @GetMapping(value = "/filter-email")
    public SuccessResponse<List<PatientResponse>> getPatientByEmailAddress(@RequestParam("email") String email) {
        return new SuccessResponse<>(patientService.getPatientByEmailAddress(email));
    }

    @GetMapping(value = "/filter-age")
    public SuccessResponse<List<PatientResponse>> getPatientByAgeLessThan(@RequestParam("age") Integer age) {
        return new SuccessResponse<>(patientService.getPatientByAgeLessThan(age));
    }

    @PostMapping(value = "/")
    public SuccessResponse<PatientResponse> createPatient(@RequestBody PatientRequest patientRequestDTO) {
        return new SuccessResponse<>(patientService.createPatient(patientRequestDTO));
    }

    @PutMapping(value = "/")
    public SuccessResponse<PatientResponse> updatePatient(@RequestBody PatientRequest patientRequestDTO) {
        return new SuccessResponse<>(patientService.updatePatient(patientRequestDTO));
    }

    @DeleteMapping(value = "/{id}")
    public SuccessResponse<String> deletePatient(@PathVariable("id") Long id) {
        patientService.deletePatient(id);
        return new SuccessResponse<>("Data deleted successfully");
    }

    @PutMapping(value = "/request-account-inactivation")
    public SuccessResponse<String> updateStatus(@RequestParam("xId") String xId){
        patientService.updateStatusRequestInactive(xId);
        return new SuccessResponse<>("Status updated successfully");
    }
}
