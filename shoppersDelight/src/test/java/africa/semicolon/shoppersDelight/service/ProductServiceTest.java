package africa.semicolon.shoppersDelight.service;

import africa.semicolon.shoppersDelight.dto.reponse.AddProductResponse;
import africa.semicolon.shoppersDelight.dto.request.AddProductRequest;
import africa.semicolon.shoppersDelight.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test public void testAddProduct(){
        AddProductRequest productRequest = new AddProductRequest();
        productRequest.setName("Cloth");
        productRequest.setPrice(BigDecimal.TEN);
        productRequest.setDescription("Yummy,yum,yum");
        productRequest.setQuantity(10);

        AddProductResponse response =  productService.addProduct(productRequest);
        log.info("Product added :: {} ",response);
        assertThat(response).isNotNull();
    }
    @Test
    @Sql(scripts = {"/scripts/insert.sql"})
    public void getProductTest(){

    }

}
