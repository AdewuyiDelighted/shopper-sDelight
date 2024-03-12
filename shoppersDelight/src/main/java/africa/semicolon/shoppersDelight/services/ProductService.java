package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dto.reponse.AddProductResponse;
import africa.semicolon.shoppersDelight.dto.reponse.ProductResponse;
import africa.semicolon.shoppersDelight.dto.request.AddProductRequest;

import java.util.List;

public interface ProductService {

    AddProductResponse addProduct(AddProductRequest productRequest);
    ProductResponse getProductBy(Long id);
    List<ProductResponse> getProducts(int page, int size);

}
