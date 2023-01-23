package ru.test.customerservice.customerservice.validation;

import org.springframework.stereotype.Component;
import ru.test.customerservice.customerservice.model.CustomerDto;

@Component
public class BankSourceValidator implements CustomerCreationValidator {
    @Override
    public boolean validate(CustomerDto customerDto) {
        return validateBankId()
                .and(validateLastname())
                .and(validateFirstName())
                .and(validatePatronymic())
                .and(validateBirthdate())
                .and(validatePassportNumber())
                .test(customerDto);
    }

    @Override
    public boolean supports(CustomerCreationSource delimiter) {
        return delimiter.equals(CustomerCreationSource.BANK);
    }
}
