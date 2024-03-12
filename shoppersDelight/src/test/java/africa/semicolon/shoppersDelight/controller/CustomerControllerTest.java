package africa.semicolon.shoppersDelight.controller;


import africa.semicolon.shoppersDelight.dto.request.CustomerRegistrationRequest;
import africa.semicolon.shoppersDelight.dto.request.UpdateCustomerRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testRegisterCustomer() throws Exception {
        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest();
        customerRegistrationRequest.setEmail("Semicolon@gmail.com");
        customerRegistrationRequest.setPassword("Password");
        customerRegistrationRequest.setAddress("312,Herbert marculay,Yaba");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(customerRegistrationRequest)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }

    @Test public void testThatCustomerCanUpdate() throws Exception {
        UpdateCustomerRequest updateCustomerRequest = new UpdateCustomerRequest();
        updateCustomerRequest.setEmail("test@gmail.com");
        updateCustomerRequest.setPhoneNumber("090123456");


        mockMvc.perform(patch("/api/v1/customer/2")
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(updateCustomerRequest)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }


}
