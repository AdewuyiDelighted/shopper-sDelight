package africa.semicolon.shoppersDelight.controller;

import africa.semicolon.shoppersDelight.dto.reponse.ApiResponse;
import africa.semicolon.shoppersDelight.dto.reponse.CustomerRegistrationResponse;
import africa.semicolon.shoppersDelight.dto.request.CustomerRegistrationRequest;
import africa.semicolon.shoppersDelight.dto.request.UpdateCustomerRequest;
import africa.semicolon.shoppersDelight.exceptions.CustomerNotFoundException;
import africa.semicolon.shoppersDelight.services.CustomerServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerServices customerService;

    @PostMapping
    public ResponseEntity<CustomerRegistrationResponse> registerCustomer(@RequestBody CustomerRegistrationRequest request){
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(customerService.register(request));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody UpdateCustomerRequest request, @PathVariable Long id){
        try {
            return ResponseEntity.ok(customerService.updateCustomer(id,request));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Failed"));
        }
    }


}
