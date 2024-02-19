package com.sevenspan.patient.enums;

public enum Gender {

    MALE("Male"),FEMALE("Female"),TRANSGENDER("Transgender");

    private String value;

    Gender(String gender){
        this.value = gender;
    }

    public String getValue() {
        return this.value;
    }
}
