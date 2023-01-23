package ru.test.customerservice.customerservice.utils;

import org.springframework.core.convert.converter.Converter;
import ru.test.customerservice.customerservice.validation.CustomerCreationSource;

public class StringToEnumConverter implements Converter<String, CustomerCreationSource> {
    @Override
    public CustomerCreationSource convert(String source) {
        return CustomerCreationSource.valueOf(source.toUpperCase());
    }
}
