package africa.semicolon.shoppersDelight.repository;

import africa.semicolon.shoppersDelight.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
