package com.sevenspan.patient.util;

import com.sevenspan.patient.dto.responsedto.ErrorMessage;
import com.sevenspan.patient.enums.MessageStatus;
import com.sevenspan.patient.exceptions.PMRecordExistsException;
import com.sevenspan.patient.exceptions.PMRecordNotExistsException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class ErrorUtil {

    @ExceptionHandler
    public ErrorMessage errorResponse(Exception exception){
        log.info("Default exception entry.......................");
        ErrorMessage messageDTO=new ErrorMessage(
                MessageStatus.FAILURE,
                exception.getMessage(),
                exception.getStackTrace());

        exception.printStackTrace();
        return messageDTO;
    }

    @ExceptionHandler(value = {PMRecordExistsException.class})
    public ErrorMessage errorResponse(PMRecordExistsException recordExistsException){
        log.info("Custom exception entry.......................");
        ErrorMessage messageDTO=new ErrorMessage(
                MessageStatus.FAILURE,
                recordExistsException.getMessage(),
                recordExistsException.getStackTrace());

        return messageDTO;
    }

    @ExceptionHandler(value = {PMRecordNotExistsException.class})
    public ErrorMessage errorResponse(PMRecordNotExistsException recordNotExistsException){
        log.info("Custom exception entry.......................");
        ErrorMessage messageDTO=new ErrorMessage(
                MessageStatus.FAILURE,
                recordNotExistsException.getMessage(),
                recordNotExistsException.getStackTrace());

        return messageDTO;
    }
}
