package com.sevenspan.patient.controller;

import com.sevenspan.patient.dto.requestdto.TreatmentRequest;
import com.sevenspan.patient.dto.responsedto.TreatmentResponse;
import com.sevenspan.patient.service.TreatmentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/treatment")
@Log4j2
public class TreatmentController {

    @Autowired
    TreatmentService treatmentService;

    @GetMapping(value = "/")
    public List<TreatmentResponse> getAllTreatment() {
        return treatmentService.getAllTreatment();
    }

    @GetMapping(value = "/{id}")
    public List<TreatmentResponse> getTreatmentsByPatientId(@PathVariable("id") Long id) {
        return treatmentService.getTreatmentByPatientId(id);
    }

    @PostMapping(value = "/")
    public TreatmentResponse createTreatment(@RequestBody TreatmentRequest treatmentDTO) {
        return treatmentService.createTreatment(treatmentDTO);
    }

    @PutMapping(value = "/")
    public TreatmentResponse updateTreatment(@RequestBody TreatmentRequest treatmentDTO) {
        return treatmentService.updatetreatment(treatmentDTO);
    }
}
