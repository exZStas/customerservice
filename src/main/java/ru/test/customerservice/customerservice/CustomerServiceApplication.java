package ru.test.customerservice.customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.plugin.core.config.EnablePluginRegistries;
import ru.test.customerservice.customerservice.validation.CustomerCreationValidator;

@SpringBootApplication
@EnablePluginRegistries(CustomerCreationValidator.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
