package com.sevenspan.patient.entity;

import com.sevenspan.patient.enums.Gender;
import com.sevenspan.patient.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "patient")
@NamedQueries({
        @NamedQuery(name = "findByEmailAddress",
                query = "FROM patient p WHERE p.email = ?1"),
        @NamedQuery(name = "findByAge",
                query = "SELECT p FROM patient p WHERE p.age <= ?1"),
})
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotBlank(message = "Id should not be null")
    private Long id;

    @Column(name = "doctor_x_id")
    private String doctorXid;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "age")
    private Integer age;

    //Male, Female, Transgender
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    @NotBlank(message = "Phone number should not be null")
    @Pattern(regexp = "(^$|[0-9]{10})")
    private Long phoneNumber;

    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @Column(name = "email")
    private String email;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    //ACTIVE
    //INACTIVE
    //INACTIVATION_REQUESTED
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    @Column(name = "date_created")
    private LocalDate dateCreated = LocalDate.now();

    @Column(name = "date_modified")
    private LocalDate dateModified = LocalDate.now();

    @Column(name = "x_id", unique = true, nullable = false, updatable = false)
    private String xid;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private List<TreatmentEntity> treatmentEntity;

    @PrePersist
    public void autoFillUuid() {
        this.setXid(UUID.randomUUID().toString());
    }

}
