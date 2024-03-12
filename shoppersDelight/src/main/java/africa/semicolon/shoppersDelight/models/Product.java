package africa.semicolon.shoppersDelight.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    private Integer quantity;
    private String name;
    private String description;
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void setCreatedAt() {

        this.createdAt = createdAt;
    }

    @PreUpdate
    public void setUpdatedAt() {

        this.updatedAt = updatedAt;
    }



}
