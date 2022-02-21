package com.sevenspan.patient.enums;

public enum MessageStatus {
    SUCCESS("Success"),FAILURE("Failure");

    private String value;

    MessageStatus(String message){
        this.value = message;
    }

    public String getValue() {
        return this.value;
    }
}
