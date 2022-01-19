package com.sevenspan.patient.controller.treatmentcontroller;

import com.sevenspan.patient.dto.requestdto.treatmentdto.TreatmentRequestDTO;
import com.sevenspan.patient.dto.responsedto.messagedto.ErrorMessageDTO;
import com.sevenspan.patient.dto.responsedto.messagedto.SuccessMessageDTO;
import com.sevenspan.patient.enums.messagestatus.MessageStatus;
import com.sevenspan.patient.service.treatmentservice.TreatmentService;
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
    public SuccessMessageDTO<List<TreatmentRequestDTO>> getAllTreatment() {
        log.info("Enter into TreatmentController.getTreatment() method");
        return new SuccessMessageDTO<List<TreatmentRequestDTO>>(treatmentService.getAllTreatment());
    }

    @GetMapping(value = "/{id}")
    public SuccessMessageDTO<List<TreatmentRequestDTO>> getTreatmentsByPatientId(@PathVariable("id") Long id) {
        log.info("Enter into TreatmentController.getTreatmentsByPatientId() method");
        return new SuccessMessageDTO<>(treatmentService.getTreatmentByPatientId(id));
    }

    @PostMapping(value = "/")
    public SuccessMessageDTO<TreatmentRequestDTO> createTreatment(@RequestBody TreatmentRequestDTO treatmentDTO) {
        log.info("Enter into TreatmentController.createTreatment() method");
        return new SuccessMessageDTO<>(treatmentService.createTreatment(treatmentDTO));
    }

    @PutMapping(value = "/")
    public SuccessMessageDTO<TreatmentRequestDTO> updateTreatment(@RequestBody TreatmentRequestDTO treatmentDTO) {
        log.info("Enter into TreatmentController.updateTreatment() method");
        return new SuccessMessageDTO<>(treatmentService.updatetreatment(treatmentDTO));
    }
}
