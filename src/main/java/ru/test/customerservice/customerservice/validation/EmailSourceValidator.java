package ru.test.customerservice.customerservice.validation;

import org.springframework.stereotype.Component;
import ru.test.customerservice.customerservice.model.CustomerDto;

@Component
public class EmailSourceValidator implements CustomerCreationValidator {
    @Override
    public boolean validate(CustomerDto customerDto) {
        return validateFirstName()
                .and(validateEmail())
                .test(customerDto);
    }

    @Override
    public boolean supports(CustomerCreationSource delimiter) {
        return delimiter.equals(CustomerCreationSource.EMAIL);
    }
}
