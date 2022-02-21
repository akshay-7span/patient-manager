package com.sevenspan.patient.mapper;

import com.sevenspan.patient.dto.requestdto.TreatmentRequest;
import com.sevenspan.patient.dto.requestdto.patientdto.PatientRequest;
import com.sevenspan.patient.dto.responsedto.PatientResponse;
import com.sevenspan.patient.dto.responsedto.TreatmentResponse;
import com.sevenspan.patient.entity.PatientEntity;
import com.sevenspan.patient.entity.TreatmentEntity;
import com.sevenspan.patient.enums.Gender;
import com.sevenspan.patient.enums.UserStatus;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {


    @Mapping(source = "dateOfBirth", target = "age", qualifiedByName = "birthDateToAge")
    @Mapping(source = "gender",target = "gender", qualifiedByName = "genderStringToEnum")
    PatientEntity mapPatientRequestToPatientEntity(PatientRequest patientRequest);

    @Named("birthDateToAge")
    public static Integer birthDateToAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    @Named("genderEnumToString")
    public static String genderEnumToString(Gender gender){
        return gender.getValue();
    }

    @Named("genderStringToEnum")
    public static String genderStringToEnum(String value){
        return value.toUpperCase();
    }

    @Named("userStatusEnumToString")
    public static String userStatusEnumToString(UserStatus userStatus){
        return userStatus.getValue();
    }

    @Mapping(target = "treatmentResponse", source = "treatmentEntity")
    @Mapping(source = "gender",target = "gender", qualifiedByName = "genderEnumToString")
    @Mapping(source = "status",target = "status", qualifiedByName = "userStatusEnumToString")
    PatientResponse mapPatientEntityToPatientResponse(PatientEntity patientEntity);

    List<TreatmentResponse> mapTreatmentEntityToTreatmentResponse(List<TreatmentEntity> treatmentEntity);

    TreatmentEntity mapTreatmentRequestToTreatmentEntity(TreatmentRequest treatmentRequest);

    TreatmentResponse mapTreatmentEntityToTreatmentResponse(TreatmentEntity treatmentEntity);
}