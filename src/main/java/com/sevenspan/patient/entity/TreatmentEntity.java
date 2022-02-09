package com.sevenspan.patient.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="treatment")
@Table(name="treatment")
public class TreatmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotBlank(message = "Id should not be null")
    private Long id;

    @Column(name = "patient_id")
    private Long patientId;

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

    @Column(name = "x_id",unique = true, nullable = false,updatable = false)
    private String xId;

    @PrePersist
    public void autoFillUuid() {
        this.setXId(UUID.randomUUID().toString());
    }

}
