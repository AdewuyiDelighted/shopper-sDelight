package africa.semicolon.shoppersDelight.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerRegistrationRequest {
    private String email;
    private String password;
    private String address;

}
