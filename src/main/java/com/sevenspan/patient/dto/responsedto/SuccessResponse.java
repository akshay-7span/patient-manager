package com.sevenspan.patient.dto.responsedto;

import com.sevenspan.patient.enums.MessageStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse<T> {

    private Enum status;
    private String message;
    private T data;

    public SuccessResponse(T data){
        this.data=data;
        this.status= MessageStatus.SUCCESS;
        this.message="Operation successful";
    }
}
