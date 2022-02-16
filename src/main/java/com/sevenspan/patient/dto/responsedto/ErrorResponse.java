package com.sevenspan.patient.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse<T> {

    private Enum status;
    private String message;
    private T data;

    public ErrorResponse(Enum status, String message) {
        this.status = status;
        this.message = message;
    }
}
