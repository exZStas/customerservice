package ru.test.customerservice.customerservice.utils;

import org.mapstruct.Mapper;
import ru.test.customerservice.customerservice.model.Customer;
import ru.test.customerservice.customerservice.model.CustomerDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto customerToDto(Customer customer);

    List<CustomerDto> customersToDtos(List<Customer> customers);
}
