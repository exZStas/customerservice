package ru.test.customerservice.customerservice.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CustomerDto {

    private Long bankId;
    private String lastname;
    private String firstName;
    private String patronymic;
    private Date birthdate;
    private String passportNumber;
    private String birthCity;
    private String phoneNumber;
    private String email;
    private String registrationAddress;
    private String residenceAddress;


}
