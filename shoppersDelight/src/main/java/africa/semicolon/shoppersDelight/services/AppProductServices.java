package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dto.reponse.AddProductResponse;
import africa.semicolon.shoppersDelight.dto.request.AddProductRequest;
import africa.semicolon.shoppersDelight.models.Product;
import africa.semicolon.shoppersDelight.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppProductServices implements ProductService{

    private ProductRepository productRepository;

    @Override
    public AddProductResponse addProduct(AddProductRequest productRequest) {
        ModelMapper mapper = new ModelMapper();
        Product product = mapper.map(productRequest, Product.class);
        Product savedProduct = productRepository.save(product);
        return mapper.map(savedProduct,AddProductResponse.class);
    }
}
