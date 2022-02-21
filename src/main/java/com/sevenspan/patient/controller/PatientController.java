package com.sevenspan.patient.controller;

import com.sevenspan.patient.dto.requestdto.patientdto.PatientRequest;
import com.sevenspan.patient.dto.requestdto.patientdto.PatientFilterRequest;
import com.sevenspan.patient.dto.responsedto.PatientResponse;
import com.sevenspan.patient.entity.PatientEntity;
import com.sevenspan.patient.entity.TreatmentEntity;
import com.sevenspan.patient.repository.PatientRepository;
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
    public PatientResponse getPatientById(@PathVariable("id") String id) {
//        List<TreatmentEntity> treatmentEntities=patientRepository.findByXid(id).getTreatmentEntity();

//        return treatmentEntities;
        return patientService.getPatientById(id);
    }

    @GetMapping(value = "/filter-phone-email")
    public List<PatientResponse> getPatientByPhoneNumberAndEmail(
            @RequestParam("phoneNumber") Long phoneNumber
            ,@RequestParam("email") String email
    ) {
        return patientService.getPatientByPhoneNumberAndEmail(phoneNumber, email);
    }

    @GetMapping(value = "/filter-email-end")
    public List<PatientResponse> getPatientByEmailEndsWith(@RequestParam("emailEnd") String emailEnd) {
        return patientService.getPatientByEmailEndsWith(emailEnd);
    }

    @GetMapping(value="/expose")
    public List<PatientResponse> getPatientByDoctorXid(
            @RequestParam("doctorXid") String doctorXid
            ,@RequestParam("pageNumber") Integer pageNumber
            ,@RequestParam("pageSize") Integer pageSize
            ,@RequestParam("sortBy") String sortBy
    ){
        return patientService.getPatientByDoctorXid(doctorXid,pageNumber,pageSize,sortBy);
    }

    @GetMapping(value = "/filter/")
    public List<PatientResponse> getPatientByGivenFilter(@RequestBody PatientFilterRequest patientFilterDTO) {
        return patientService.getPatientByGivenFilter(patientFilterDTO);
    }

    @GetMapping(value = "/filter-email")
    public List<PatientResponse> getPatientByEmailAddress(@RequestParam("email") String email) {
        return patientService.getPatientByEmailAddress(email);
    }

    @GetMapping(value = "/filter-age")
    public List<PatientResponse> getPatientByAgeLessThan(@RequestParam("age") Integer age) {
        return patientService.getPatientByAgeLessThan(age);
    }

    @PostMapping(value = "/")
    public PatientResponse createPatient(@RequestBody PatientRequest patientRequestDTO) {
        return patientService.createPatient(patientRequestDTO);
    }

    @PutMapping(value = "/")
    public PatientResponse updatePatient(@RequestBody PatientRequest patientRequestDTO) {
        return patientService.updatePatient(patientRequestDTO);
    }

    @PutMapping(value = "/delete/")
    public String deletePatient(@RequestParam("xId") String xId) {
        patientService.deletePatient(xId);
        return "Data deleted successfully";
    }

    @PutMapping(value = "/request-account-inactivation")
    public String updateStatus(@RequestParam("xId") String xId){
        patientService.updateStatusRequestInactive(xId);
        return "Status updated successfully";
    }
}
