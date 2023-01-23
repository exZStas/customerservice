package ru.test.customerservice.customerservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.test.customerservice.customerservice.model.Customer;
import ru.test.customerservice.customerservice.model.CustomerDto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void missingXsourceHeader() throws Exception {
        //when
        this.mockMvc.perform(MockMvcRequestBuilders.post("/customer")
                                .header("Content-Type", "application/json")
                                .content(asJsonString(CustomerDto.builder().lastname("doe").build())
                ))
        //then
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Required header 'x-source' is not present."));
    }

    @Test
    public void createCustomer_sourceEmail() throws Exception {
        //given
        String customerName = "john";
        String email = "dev@swe.com";
        Customer customer = Customer.builder()
                .firstName(customerName)
                .email(email)
                .build();

        when(customerService.createCustomer(any(), any())).thenReturn(customer);

        //when
        this.mockMvc.perform(MockMvcRequestBuilders.post("/customer")
                        .header("Content-Type", "application/json")
                        .header("x-source", "email")
                        .content(asJsonString(CustomerDto.builder().firstName("john").email("dev@swe.com").build())
                        ))
        //then
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
