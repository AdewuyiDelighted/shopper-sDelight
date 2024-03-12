package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dto.reponse.ApiResponse;
import africa.semicolon.shoppersDelight.dto.reponse.CustomerRegistrationResponse;
import africa.semicolon.shoppersDelight.dto.reponse.UpdateCustomerResponse;
import africa.semicolon.shoppersDelight.dto.request.CustomerRegistrationRequest;
import africa.semicolon.shoppersDelight.dto.request.UpdateCustomerRequest;
import africa.semicolon.shoppersDelight.exceptions.CustomerNotFoundException;
import africa.semicolon.shoppersDelight.models.Customer;
import africa.semicolon.shoppersDelight.repository.CustomerRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.Arrays.stream;
@Service
@AllArgsConstructor
public class AppCustomerService implements CustomerServices {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerRegistrationResponse register(CustomerRegistrationRequest request) {
        Customer customer = new Customer();
        customer.setEmail(request.getEmail());
        customer.setPassword(request.getPassword());
        Customer savedCustomer = customerRepository.save(customer);

        CustomerRegistrationResponse response = new CustomerRegistrationResponse();
        response.setId(savedCustomer.getId());

        return response;
    }

    @Override
    public ApiResponse<UpdateCustomerResponse> updateCustomer(Long id, UpdateCustomerRequest request) throws CustomerNotFoundException {
//        chect json patch documentation (The Patch section) java only accept replace

        Customer customer = findCustomerBy(id);

        List<JsonPatchOperation> jsonPatchOperations = new ArrayList<>();
        buildPatchOperations(request, jsonPatchOperations);
        customer = applyPatch(jsonPatchOperations, customer);
        customerRepository.save(customer);
        return new ApiResponse<>(buildPatchCustomerResponse());
    }

    private static UpdateCustomerResponse buildPatchCustomerResponse() {
        UpdateCustomerResponse response = new UpdateCustomerResponse();
        response.setMessage("Customer information updated successfully");
        return response;
    }


    public static void buildPatchOperations(UpdateCustomerRequest request, List<JsonPatchOperation> jsonPatchOperations) {
        Class<?> c = request.getClass();
        Field[] fields = c.getDeclaredFields();

        stream(request.getClass().getDeclaredFields()) // getting the field from the class he got from the object breakdown is aabove
                .filter(x -> isValidUpdate(x, request))  // x stand for each field that is been checked by the isValidate method
                .forEach(x -> addOperation(request, x, jsonPatchOperations));
    }

    public static Customer applyPatch(List<JsonPatchOperation> jsonPatchOperations, Customer customer) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonPatch jsonPatch = new JsonPatch(jsonPatchOperations);
            JsonNode customerNode = mapper.convertValue(customer, JsonNode.class); // inorder to use the apply() method we need a nodeValue so customer was convert to Jsonnode
            JsonNode updateNode = jsonPatch.apply(customerNode);
            customer = mapper.convertValue(updateNode, Customer.class); // converted it back to the Customer class
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());

        }
        return customer;
    }

    private static boolean isValidUpdate(Field field, UpdateCustomerRequest request) {
        field.setAccessible(true);
        try {
            return field.get(request) != null;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addOperation(UpdateCustomerRequest request, Field field, List<JsonPatchOperation> jsonPatchOperations) {
        try {
            JsonPointer path = new JsonPointer("/" +field.getName());
            JsonNode value = new TextNode(field.get(request).toString());
            ReplaceOperation replaceOperation = new ReplaceOperation(path,value);
            jsonPatchOperations.add(replaceOperation);
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }




    private Customer findCustomerBy(Long id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException(

                                String.format("Customer with id %d not found ", id)
                        ));
        return customer;
    }


}
