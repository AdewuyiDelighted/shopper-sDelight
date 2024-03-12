package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dto.reponse.AddProductResponse;
import africa.semicolon.shoppersDelight.dto.reponse.ProductResponse;
import africa.semicolon.shoppersDelight.dto.request.AddProductRequest;
import africa.semicolon.shoppersDelight.exceptions.ProductNotFoundException;
import africa.semicolon.shoppersDelight.models.Product;
import africa.semicolon.shoppersDelight.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.math.BigInteger.ONE;

@Slf4j
@Service
@AllArgsConstructor
public class AppProductServices implements ProductService{

    private ProductRepository productRepository;
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 1;
    private static final ModelMapper mapper = new ModelMapper();

    @Override
    public AddProductResponse addProduct(AddProductRequest productRequest) {
        ModelMapper mapper = new ModelMapper();
        Product product = mapper.map(productRequest, Product.class);
        Product savedProduct = productRepository.save(product);
        return mapper.map(savedProduct,AddProductResponse.class);
    }

    @Override
    public ProductResponse getProductBy(Long id) {
        return mapper.map(findById(id), ProductResponse.class);

    }

    @Override
    public List<ProductResponse> getProducts(int page, int size) {
        Page<Product> productPage = productRepository.findAll(createPageRequest(page,size));
        log.info("page--->{}",productPage);
        return  productPage.getContent()
                .stream()
                .map(product -> mapper.map(product,ProductResponse.class))
                .toList();
    }

    private static PageRequest createPageRequest(int page, int size){
        if(page < DEFAULT_PAGE_NUMBER) page = DEFAULT_PAGE_NUMBER;
        if(page < ONE.intValue()) size = DEFAULT_PAGE_SIZE;
        page = page - ONE.intValue();
        return PageRequest.of(page,size);

    }
    private Product findById(Long id){
        return  productRepository.findById(id).orElseThrow(
                ()->new ProductNotFoundException("Product not found"));
    }

}
