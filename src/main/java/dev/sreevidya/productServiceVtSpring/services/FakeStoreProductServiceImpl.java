package dev.sreevidya.productServiceVtSpring.services;

import dev.sreevidya.productServiceVtSpring.clients.FakeStoreClient;
import dev.sreevidya.productServiceVtSpring.clients.FakeStoreProductDto;
import dev.sreevidya.productServiceVtSpring.dtos.ProductDto;
import dev.sreevidya.productServiceVtSpring.exceptions.NotFoundException;
import dev.sreevidya.productServiceVtSpring.models.Category;
import dev.sreevidya.productServiceVtSpring.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreProductServiceImpl implements ProductService{
    private final RestTemplateBuilder restTemplateBuilder;
    private final FakeStoreClient fakeStoreClient;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient){
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
    }
    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        return product;
    }
    @Override
    public List<Product> getAllProducts() {

        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getAllProducts();

        List<Product> answer = new ArrayList<>();

        for (FakeStoreProductDto productDto: fakeStoreProductDtos) {
            answer.add(convertFakeStoreProductDtoToProduct(productDto));
        }
        return answer;
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException {
        Optional<FakeStoreProductDto> productDto= fakeStoreClient.getSingleProduct(productId);
        if (productDto == null) {
            return Optional.empty();
        }

        return Optional.of(convertFakeStoreProductDtoToProduct(productDto.get()));
    }

    @Override
    public Product addNewProduct(ProductDto productReq) {
        FakeStoreProductDto fDto = fakeStoreClient.addNewProduct(productReq);
        return convertFakeStoreProductDtoToProduct(fDto);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        FakeStoreProductDto fDto = fakeStoreClient.updateProduct(productId,product);
        return convertFakeStoreProductDtoToProduct(fDto);
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return fakeStoreClient.deleteProduct(productId);
    }

    @Override
    public List<String> getAllCategories() {
        return fakeStoreClient.getAllCategories();
    }

    @Override
    public List<Product> getInCategory(String categoryName) {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getInCategory(categoryName);

        List<Product> answer = new ArrayList<>();

        for (FakeStoreProductDto productDto: fakeStoreProductDtos) {
            answer.add(convertFakeStoreProductDtoToProduct(productDto));
        }
        return answer;
    }
}
