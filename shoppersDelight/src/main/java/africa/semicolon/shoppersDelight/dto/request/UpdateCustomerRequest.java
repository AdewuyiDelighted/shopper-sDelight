package africa.semicolon.shoppersDelight.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateCustomerRequest {
    private String email;
    private String address;
    private String phoneNumber;
}
