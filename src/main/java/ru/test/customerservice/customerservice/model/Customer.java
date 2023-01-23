package ru.test.customerservice.customerservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "customer")
@Builder
@AllArgsConstructor
public class Customer {

    public Customer() {}

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "bank_id", unique = true)
    private Long bankId;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column(name = "passport_number", unique = true)
    private String passportNumber;

    @Column(name = "birth_city")
    private String birthCity;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "registration_address")
    private String registrationAddress;

    @Column(name = "residence_address")
    private String residenceAddress;

}
