package africa.semicolon.shoppersDelight.dto.reponse;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductResponse {
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

}
