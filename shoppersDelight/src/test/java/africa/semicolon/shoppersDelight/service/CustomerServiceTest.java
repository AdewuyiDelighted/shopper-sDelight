package africa.semicolon.shoppersDelight.service;

import africa.semicolon.shoppersDelight.dto.reponse.ApiResponse;
import africa.semicolon.shoppersDelight.dto.reponse.UpdateCustomerResponse;
import africa.semicolon.shoppersDelight.dto.request.UpdateCustomerRequest;
import africa.semicolon.shoppersDelight.exceptions.CustomerNotFoundException;
import africa.semicolon.shoppersDelight.services.CustomerServices;
import africa.semicolon.shoppersDelight.dto.reponse.CustomerRegistrationResponse;
import africa.semicolon.shoppersDelight.dto.request.CustomerRegistrationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerServices customerService;     //field Injection

    @Test public void registerTest(){
        CustomerRegistrationRequest request = new CustomerRegistrationRequest();
        request.setEmail("semicolon@gmail.com");
        request.setPassword("password");



        CustomerRegistrationResponse response = customerService.register(request);
        assertNotNull(response);
        assertNotNull(response.getId());

    }
    @Test public void updateCustomerTest() throws CustomerNotFoundException {
        UpdateCustomerRequest request = new UpdateCustomerRequest();
        request.setAddress("312,Herbert Marculary way");
        request.setEmail("test@gmail.com");

        ApiResponse<UpdateCustomerResponse> response = customerService.updateCustomer(1L,request);
        assertThat(response).isNotNull();
        assertThat(response.getData().getMessage()).isNotNull();

    }

}
