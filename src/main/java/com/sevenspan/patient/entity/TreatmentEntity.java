package com.sevenspan.patient.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="treatment")
@Table(name="treatment")
public class TreatmentEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "treatment_id")
    private String treatmentId;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "treatment_id")
//    private Long treatmentId;

    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "disease")
    private String disease;

    @Column(name = "symptoms")
    private String symptoms;

    @Column(name = "duration")
    private String duration;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "medicine")
    private String medicine;

    @Column(name = "resolve")
    private Boolean resolve;

    @Override
    public String toString() {
        return "TreatmentEntity{" +
                "treatmentId=" + treatmentId +
                ", patientId=" + patientId +
                ", disease='" + disease + '\'' +
                ", symptoms='" + symptoms + '\'' +
                ", duration='" + duration + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", medicine='" + medicine + '\'' +
                ", resolve=" + resolve +
                '}';
    }
}
