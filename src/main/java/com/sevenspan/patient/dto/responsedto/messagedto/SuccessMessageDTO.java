package com.sevenspan.patient.dto.responsedto.messagedto;

import com.sevenspan.patient.enums.messagestatus.MessageStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessMessageDTO<T> {

    private Enum status;
    private String message;
    private T data;

    public SuccessMessageDTO(T data){
        this.data=data;
        this.status= MessageStatus.SUCCESS;
        this.message="Operation successful";
    }
}
