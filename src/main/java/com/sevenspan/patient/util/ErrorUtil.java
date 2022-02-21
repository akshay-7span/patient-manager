package com.sevenspan.patient.util;

import com.sevenspan.patient.dto.responsedto.ErrorResponse;
import com.sevenspan.patient.enums.MessageStatus;
import com.sevenspan.patient.exceptions.PMRecordExistsException;
import com.sevenspan.patient.exceptions.PMRecordNotExistsException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.sevenspan.patient.controller")
@Log4j2
public class ErrorUtil {

    @ExceptionHandler
    public ErrorResponse errorResponse(Exception exception){
        log.info("Default exception entry.......................");
        ErrorResponse messageDTO=new ErrorResponse(
                MessageStatus.FAILURE.getValue(),
                exception.getMessage(),
                exception.getStackTrace());

        exception.printStackTrace();
        return messageDTO;
    }

    @ExceptionHandler(value = {PMRecordExistsException.class})
    public ErrorResponse errorResponse(PMRecordExistsException recordExistsException){
        log.info("Custom exception entry.......................");
        return new ErrorResponse(
                MessageStatus.FAILURE.getValue(),
                recordExistsException.getMessage(),
                recordExistsException.getStackTrace());
    }

    @ExceptionHandler(value = {PMRecordNotExistsException.class})
    public ErrorResponse errorResponse(PMRecordNotExistsException recordNotExistsException){
        log.info("Custom exception entry.......................");
        return new ErrorResponse(
                MessageStatus.FAILURE.getValue(),
                recordNotExistsException.getMessage(),
                recordNotExistsException.getStackTrace());
    }
}
