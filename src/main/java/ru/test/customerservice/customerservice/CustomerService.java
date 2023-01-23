package ru.test.customerservice.customerservice;

import org.springframework.http.HttpStatus;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;
import ru.test.customerservice.customerservice.exception.CustomerSourceValidationException;
import ru.test.customerservice.customerservice.model.Customer;
import ru.test.customerservice.customerservice.model.CustomerDto;
import ru.test.customerservice.customerservice.repository.CustomerRepository;
import ru.test.customerservice.customerservice.validation.CustomerCreationSource;
import ru.test.customerservice.customerservice.validation.CustomerCreationValidator;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PluginRegistry<CustomerCreationValidator, CustomerCreationSource> pluginRegistry;

    public CustomerService(CustomerRepository customerRepository, PluginRegistry<CustomerCreationValidator, CustomerCreationSource> pluginRegistry) {
        this.customerRepository = customerRepository;
        this.pluginRegistry = pluginRegistry;
    }

    public Customer createCustomer(CustomerDto customerDto, CustomerCreationSource creationSource){
        Assert.notNull(customerDto, "Customer can't be null");
        Assert.notNull(creationSource, "Creation source can't be null");

        CustomerCreationValidator validator = pluginRegistry.getPluginFor(creationSource)
                .orElseThrow(() -> new RuntimeException("Can't create a customer from a given source"));

        if(!validator.validate(customerDto)) {
            throw new CustomerSourceValidationException("Mandatory fields are missing for given source");
        }

        return customerRepository.save(
                Customer.builder()
                        .bankId(customerDto.getBankId())
                        .firstName(customerDto.getFirstName())
                        .lastname(customerDto.getLastname())
                        .patronymic(customerDto.getPatronymic())
                        .passportNumber(customerDto.getPassportNumber())
                        .birthdate(customerDto.getBirthdate())
                        .birthCity(customerDto.getBirthCity())
                        .email(customerDto.getEmail())
                        .phoneNumber(customerDto.getPhoneNumber())
                        .registrationAddress(customerDto.getRegistrationAddress())
                        .residenceAddress(customerDto.getResidenceAddress())
                        .build());
    }

    public Optional<Customer> getCustomer(Long customerId) {
        Assert.notNull(customerId, "Customer id can't be null");

        return customerRepository.findById(customerId);
    }

    public List<Customer> searchCustomers(String lastname, String firstName, String patronymic, String phoneNumber, String email) {
        return customerRepository.searchCustomers(lastname, firstName, patronymic, phoneNumber, email);
    }

}
