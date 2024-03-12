package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dto.reponse.ApiResponse;
import africa.semicolon.shoppersDelight.dto.reponse.CustomerRegistrationResponse;
import africa.semicolon.shoppersDelight.dto.reponse.UpdateCustomerResponse;
import africa.semicolon.shoppersDelight.dto.request.CustomerRegistrationRequest;
import africa.semicolon.shoppersDelight.dto.request.UpdateCustomerRequest;
import africa.semicolon.shoppersDelight.exceptions.CustomerNotFoundException;


public interface CustomerServices {
    CustomerRegistrationResponse register(CustomerRegistrationRequest request);

    ApiResponse<UpdateCustomerResponse> updateCustomer(Long id, UpdateCustomerRequest request) throws CustomerNotFoundException;
}
