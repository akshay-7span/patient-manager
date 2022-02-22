package com.sevenspan.patient.controller;

import com.sevenspan.patient.dto.requestdto.patientdto.PatientFilterRequest;
import com.sevenspan.patient.dto.requestdto.patientdto.PatientRequest;
import com.sevenspan.patient.dto.responsedto.PatientResponse;
import com.sevenspan.patient.exceptions.PMRecordExistsException;
import com.sevenspan.patient.exceptions.PMRecordNotExistsException;
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
    public List<PatientResponse> getAllPatients() throws PMRecordNotExistsException {
        return patientService.getAllPatients();
    }

    @GetMapping(value = "/{id}")
    public PatientResponse getPatientById(@PathVariable("id") String id) throws PMRecordNotExistsException {
        return patientService.getPatientById(id);
    }

    @GetMapping(value = "/filter-phone-email")
    public List<PatientResponse> getPatientByPhoneNumberAndEmail(
            @RequestParam("phoneNumber") Long phoneNumber
            , @RequestParam("email") String email
    ) throws PMRecordNotExistsException {
        return patientService.getPatientByPhoneNumberAndEmail(phoneNumber, email);
    }

    @GetMapping(value = "/filter-email-end")
    public List<PatientResponse> getPatientByEmailEndsWith(@RequestParam("emailEnd") String emailEnd) throws PMRecordNotExistsException {
        return patientService.getPatientByEmailEndsWith(emailEnd);
    }

    @GetMapping(value = "/expose")
    public List<PatientResponse> getPatientByDoctorXid(
            @RequestParam("doctorXid") String doctorXid
            , @RequestParam("pageNumber") Integer pageNumber
            , @RequestParam("pageSize") Integer pageSize
            , @RequestParam("sortBy") String sortBy
    ) throws PMRecordNotExistsException {
        return patientService.getPatientByDoctorXid(doctorXid, pageNumber, pageSize, sortBy);
    }

    @GetMapping(value = "/filter/")
    public List<PatientResponse> getPatientByGivenFilter(@RequestBody PatientFilterRequest patientFilterDTO) throws PMRecordNotExistsException {
        return patientService.getPatientByGivenFilter(patientFilterDTO);
    }

    @GetMapping(value = "/filter-email")
    public List<PatientResponse> getPatientByEmailAddress(@RequestParam("email") String email) throws PMRecordNotExistsException {
        return patientService.getPatientByEmailAddress(email);
    }

    @GetMapping(value = "/filter-age")
    public List<PatientResponse> getPatientByAgeLessThan(@RequestParam("age") Integer age) throws PMRecordNotExistsException {
        return patientService.getPatientByAgeLessThan(age);
    }

    @PostMapping(value = "/")
    public PatientResponse createPatient(@RequestBody PatientRequest patientRequestDTO) throws PMRecordExistsException {
        return patientService.createPatient(patientRequestDTO);
    }

    @PutMapping(value = "/")
    public PatientResponse updatePatient(@RequestBody PatientRequest patientRequestDTO) throws PMRecordNotExistsException {
        return patientService.updatePatient(patientRequestDTO);
    }

    @PutMapping(value = "/delete/")
    public String deletePatient(@RequestParam("xId") String xId) {
        patientService.deletePatient(xId);
        return "Data deleted successfully";
    }

    @PutMapping(value = "/request-account-inactivation")
    public String updateStatus(@RequestParam("xId") String xId) throws PMRecordNotExistsException {
        patientService.updateStatusRequestInactive(xId);
        return "Status updated successfully";
    }
}
