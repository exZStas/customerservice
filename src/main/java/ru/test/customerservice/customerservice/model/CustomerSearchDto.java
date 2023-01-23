package ru.test.customerservice.customerservice.model;

import lombok.Data;

@Data
public class CustomerSearchDto {

    private String lastname;
    private String firstName;
    private String patronymic;
    private String phoneNumber;
    private String email;
}
