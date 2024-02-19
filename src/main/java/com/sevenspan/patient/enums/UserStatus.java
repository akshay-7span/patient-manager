package com.sevenspan.patient.enums;

public enum UserStatus {

    ACTIVE("Active"),INACTIVE("Inactive"),INACTIVATION_REQUESTED("Inactivation_Requested");

    private String value;

    UserStatus(String status){
        this.value = status;
    }

    public String getValue() {
        return this.value;
    }
}
