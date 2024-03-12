package africa.semicolon.shoppersDelight.dto.reponse;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Setter
@Getter
public class AddProductResponse {
    private Long id;
    private BigDecimal price;
    private Integer quantity;
    private String name;
    private String description;
}
