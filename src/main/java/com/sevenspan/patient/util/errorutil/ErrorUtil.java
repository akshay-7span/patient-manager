package com.sevenspan.patient.util.errorutil;

import com.sevenspan.patient.dto.responsedto.messagedto.ErrorMessageDTO;
import com.sevenspan.patient.enums.messagestatus.MessageStatus;
import com.sevenspan.patient.exceptions.pmexceptions.PMRecordExistsException;
import com.sevenspan.patient.exceptions.pmexceptions.PMRecordNotExistsException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class ErrorUtil {

    @ExceptionHandler
    public ErrorMessageDTO errorResponse(Exception exception){
        log.info("Default exception entry.......................");
        ErrorMessageDTO messageDTO=new ErrorMessageDTO(
                MessageStatus.FAILURE,
                exception.getMessage(),
                exception.getStackTrace());

        exception.printStackTrace();
        return messageDTO;
    }

    @ExceptionHandler(value = {PMRecordExistsException.class})
    public ErrorMessageDTO errorResponse(PMRecordExistsException recordExistsException){
        log.info("Custom exception entry.......................");
        ErrorMessageDTO messageDTO=new ErrorMessageDTO(
                MessageStatus.FAILURE,
                recordExistsException.getMessage(),
                recordExistsException.getStackTrace());

        return messageDTO;
    }

    @ExceptionHandler(value = {PMRecordNotExistsException.class})
    public ErrorMessageDTO errorResponse(PMRecordNotExistsException recordNotExistsException){
        log.info("Custom exception entry.......................");
        ErrorMessageDTO messageDTO=new ErrorMessageDTO(
                MessageStatus.FAILURE,
                recordNotExistsException.getMessage(),
                recordNotExistsException.getStackTrace());

        return messageDTO;
    }
}
