package com.sevenspan.patient.controller;

import com.sevenspan.patient.dto.requestdto.TreatmentRequest;
import com.sevenspan.patient.dto.responsedto.SuccessResponse;
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
    public SuccessResponse<List<TreatmentResponse>> getAllTreatment() {
        return new SuccessResponse<>(treatmentService.getAllTreatment());
    }

    @GetMapping(value = "/{id}")
    public SuccessResponse<List<TreatmentResponse>> getTreatmentsByPatientId(@PathVariable("id") Long id) {
        return new SuccessResponse<>(treatmentService.getTreatmentByPatientId(id));
    }

    @PostMapping(value = "/")
    public SuccessResponse<TreatmentResponse> createTreatment(@RequestBody TreatmentRequest treatmentDTO) {
        return new SuccessResponse<>(treatmentService.createTreatment(treatmentDTO));
    }

    @PutMapping(value = "/")
    public SuccessResponse<TreatmentResponse> updateTreatment(@RequestBody TreatmentRequest treatmentDTO) {
        return new SuccessResponse<>(treatmentService.updatetreatment(treatmentDTO));
    }
}
