package ru.test.customerservice.customerservice.validation;

import org.springframework.stereotype.Component;
import ru.test.customerservice.customerservice.model.CustomerDto;

@Component
public class GosuslugiValidator implements CustomerCreationValidator{
    @Override
    public boolean validate(CustomerDto customerDto) {
        return validateFirstName()
                .and(validateLastname())
                .and(validatePatronymic())
                .and(validateBirthdate())
                .and(validateBirthCity())
                .and(validatePassportNumber())
                .and(validatePhoneNumber())
                .and(validateResidenceAddress())
                .and(validateBankId())
                .test(customerDto);
    }

    @Override
    public boolean supports(CustomerCreationSource delimiter) {
        return delimiter.equals(CustomerCreationSource.GOSUSLUGI);
    }
}
