package ru.test.customerservice.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNPROCESSABLE_ENTITY, reason="Mandatory fields are missing for given source")
public class CustomerSourceValidationException extends RuntimeException {

    public CustomerSourceValidationException(String message) {
        super(message);
    }
}
