package com.sevenspan.patient.dto.requestdto.patientdto;

import lombok.Data;

import java.sql.Date;

@Data
public class PatientRequest {

    private Long id;
    private Long doctorId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Integer age;
    private String gender;
    private String address;
    private Long phoneNumber;
    private String email;
    private String marritalStatus;
    private String prefferedLanguage;
    private Date dateCreated;
    private Date dateModified;
    private String xId;

    @Override
    public String toString() {
        return "PatientRequest{" +
                "id=" + id +
                ", doctorId=" + doctorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", marritalStatus='" + marritalStatus + '\'' +
                ", prefferedLanguage='" + prefferedLanguage + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                ", xId='" + xId + '\'' +
                '}';
    }
}
