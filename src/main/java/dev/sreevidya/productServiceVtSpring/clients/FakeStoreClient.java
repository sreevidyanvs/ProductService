package dev.sreevidya.productServiceVtSpring.clients;

import dev.sreevidya.productServiceVtSpring.dtos.ProductDto;
import dev.sreevidya.productServiceVtSpring.exceptions.NotFoundException;
import dev.sreevidya.productServiceVtSpring.models.Category;
import dev.sreevidya.productServiceVtSpring.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class FakeStoreClient {

    private final RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();

        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> l = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        return Arrays.asList(l.getBody());
    }

    public Optional<FakeStoreProductDto>getSingleProduct(Long productId) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =  restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class, productId);

        FakeStoreProductDto productDto = response.getBody();

        if (productDto == null) {
            return Optional.empty();
        }

        return Optional.of((productDto));


    }

    public FakeStoreProductDto addNewProduct(ProductDto productReq) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                productReq,
                FakeStoreProductDto.class
        );
        return response.getBody();

    }

    /*
    Product object has only those fields filled which need to be updated.
    Everything else is null
     */
    public FakeStoreProductDto updateProduct(Long productId, Product product) {

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        ResponseEntity<FakeStoreProductDto> res = requestForEntity(HttpMethod.PATCH,"https://fakestoreapi.com/products/{id}",fakeStoreProductDto,FakeStoreProductDto.class,productId);
            return res.getBody();

    }
    // if (product.getImageUrl() != null) {
    //
    // }
    FakeStoreProductDto replaceProduct(Long productId, Product product) {
        return null;
    }

    public boolean deleteProduct(Long productId) {

        RestTemplate restTemp = restTemplateBuilder.build();
         restTemp.delete("https://fakestoreapi.com/products/{id}",
                 productId);
        return true;
    }

    public List<String> getAllCategories()
    {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<String[]> l = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/categories",
                String[].class
        );

        return Arrays.asList(l.getBody());
    }

    public List<FakeStoreProductDto> getInCategory(String categoryName)
    {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> l = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/category/{cName}",
                FakeStoreProductDto[].class,categoryName);

        return Arrays.asList(l.getBody());
    }

}
