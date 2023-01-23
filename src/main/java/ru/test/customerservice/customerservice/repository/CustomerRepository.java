package ru.test.customerservice.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.test.customerservice.customerservice.model.Customer;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryCustom {

}
