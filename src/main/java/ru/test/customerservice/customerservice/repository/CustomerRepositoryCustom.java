package ru.test.customerservice.customerservice.repository;

import ru.test.customerservice.customerservice.model.Customer;

import java.util.List;

public interface CustomerRepositoryCustom {

    List<Customer> searchCustomers(String lastname, String firstName, String patronymic, String phoneNumber, String email);
}
