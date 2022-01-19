package com.sevenspan.patient.dto.responsedto.messagedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageDTO<T> {

    private Enum status;
    private String message;
    private T data;

    public ErrorMessageDTO(Enum status, String message) {
        this.status = status;
        this.message = message;
    }
}
