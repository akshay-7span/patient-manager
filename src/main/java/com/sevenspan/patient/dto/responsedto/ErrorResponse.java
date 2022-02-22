package com.sevenspan.patient.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse<T> {

    private String status;
    private String message;
    private T data;

    public ErrorResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
