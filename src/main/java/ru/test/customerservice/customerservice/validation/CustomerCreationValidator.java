package ru.test.customerservice.customerservice.validation;

import org.springframework.plugin.core.Plugin;
import ru.test.customerservice.customerservice.model.CustomerDto;

import java.util.function.Predicate;

import static java.util.Objects.nonNull;
import static org.apache.logging.log4j.util.Strings.isNotBlank;

public interface CustomerCreationValidator extends Plugin<CustomerCreationSource> {
    boolean validate(CustomerDto customerDto);

    default Predicate<CustomerDto> validateFirstName() {
        return customer -> isNotBlank(customer.getFirstName());
    }

    default Predicate<CustomerDto> validateEmail() {
        return customer -> isNotBlank(customer.getEmail());
    }

    default Predicate<CustomerDto> validatePhoneNumber() {
        return customer -> isNotBlank(customer.getPhoneNumber());
    }

    default Predicate<CustomerDto> validateBankId() {
        return customer -> nonNull(customer.getBankId());
    }

    default Predicate<CustomerDto> validateLastname() {
        return customer -> isNotBlank(customer.getLastname());
    }

    default Predicate<CustomerDto> validatePatronymic() {
        return customer -> isNotBlank(customer.getPatronymic());
    }

    default Predicate<CustomerDto> validateBirthdate() {
        return customer -> nonNull(customer.getBirthdate());
    }

    default Predicate<CustomerDto> validatePassportNumber() {
        return customer -> nonNull(customer.getPassportNumber());
    }

    default Predicate<CustomerDto> validateRegistrationAddress() {
        return customer -> nonNull(customer.getRegistrationAddress());
    }

    default Predicate<CustomerDto> validateResidenceAddress() {
        return customer -> nonNull(customer.getResidenceAddress());
    }

    default Predicate<CustomerDto> validateBirthCity() {
        return customer -> nonNull(customer.getBirthCity());
    }

}
