package ru.test.customerservice.customerservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.test.customerservice.customerservice.model.Customer;
import ru.test.customerservice.customerservice.model.CustomerDto;
import ru.test.customerservice.customerservice.model.CustomerSearchDto;
import ru.test.customerservice.customerservice.utils.CustomerMapper;
import ru.test.customerservice.customerservice.validation.CustomerCreationSource;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto, @RequestHeader(name = "x-source") CustomerCreationSource creationSource) {
        Customer customer = customerService.createCustomer(customerDto, creationSource);
        return new ResponseEntity<>(customerMapper.customerToDto(customer), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{customerId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long customerId) {
       return customerService.getCustomer(customerId)
                .map(customerMapper::customerToDto)
                .map(dto -> new ResponseEntity(dto, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDto>> getCustomers(@RequestBody CustomerSearchDto customerSearchDto) {
        //todo implement more careful fields check. E.g. strings might not be nulls, but empty etc
        boolean isDtoEmpty = Stream.of(customerSearchDto.getClass().getDeclaredMethods()).allMatch(Objects::isNull);
        if(isDtoEmpty) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "At least one search field should be present");
        }
        List<Customer> customers = customerService.searchCustomers(customerSearchDto.getLastname(), customerSearchDto.getFirstName(),
                customerSearchDto.getPatronymic(), customerSearchDto.getPhoneNumber(), customerSearchDto.getEmail());

        List<CustomerDto> customerDtos = customerMapper.customersToDtos(customers);

        return new ResponseEntity<>(customerDtos, HttpStatus.OK);
    }

}
