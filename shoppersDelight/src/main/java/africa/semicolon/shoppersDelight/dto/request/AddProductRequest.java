package africa.semicolon.shoppersDelight.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class AddProductRequest {
    private Long id;
    private BigDecimal price;
    private Integer quantity;
    private String name;
    private String description;


}
