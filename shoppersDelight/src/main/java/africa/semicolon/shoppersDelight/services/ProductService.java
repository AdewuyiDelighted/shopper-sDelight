package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dto.reponse.AddProductResponse;
import africa.semicolon.shoppersDelight.dto.request.AddProductRequest;

public interface ProductService {

    AddProductResponse addProduct(AddProductRequest productRequest);

}
