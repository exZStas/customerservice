package ru.test.customerservice.customerservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.test.customerservice.customerservice.exception.CustomerSourceValidationException;
import ru.test.customerservice.customerservice.model.Customer;
import ru.test.customerservice.customerservice.model.CustomerDto;
import ru.test.customerservice.customerservice.repository.CustomerRepository;
import ru.test.customerservice.customerservice.validation.CustomerCreationSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;
    @MockBean
    private CustomerRepository customerRepository;

    @Test
    public void createCustomer_EmailSource_success() {
        String firstName = "john";
        String email = "dev@swe.com";
        CustomerDto customerDto = CustomerDto.builder().email(email).firstName(firstName).build();
        Customer customer = Customer.builder()
                .firstName(firstName)
                .email(email)
                .build();
        when(customerRepository.save(any())).thenReturn(customer);

        Customer createCustomer = customerService.createCustomer(customerDto, CustomerCreationSource.EMAIL);

        Assertions.assertNotNull(createCustomer);
        Assertions.assertEquals(firstName, createCustomer.getFirstName());
        Assertions.assertEquals(email, createCustomer.getEmail());
    }

    @Test
    public void createCustomer_EmailSource_fail() {
        String firstName = "john";
        CustomerDto customerDto = CustomerDto.builder().firstName(firstName).build();
        Customer customer = Customer.builder()
                .firstName(firstName)
                .build();
        when(customerRepository.save(any())).thenReturn(customer);

        Throwable exception = assertThrows(CustomerSourceValidationException.class, () -> customerService.createCustomer(customerDto, CustomerCreationSource.EMAIL));
        assertEquals("Mandatory fields are missing for given source", exception.getMessage());
    }
}
