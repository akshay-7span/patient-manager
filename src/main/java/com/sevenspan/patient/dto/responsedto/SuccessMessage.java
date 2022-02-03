package com.sevenspan.patient.dto.responsedto;

import com.sevenspan.patient.enums.MessageStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessMessage<T> {

    private Enum status;
    private String message;
    private T data;

    public SuccessMessage(T data){
        this.data=data;
        this.status= MessageStatus.SUCCESS;
        this.message="Operation successful";
    }
}
