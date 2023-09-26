package dev.sreevidya.productServiceVtSpring.services;

import dev.sreevidya.productServiceVtSpring.dtos.ProductDto;
import dev.sreevidya.productServiceVtSpring.models.Category;
import dev.sreevidya.productServiceVtSpring.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class FakeStoreProductServiceImpl implements ProductService{
    private final RestTemplateBuilder restTemplateBuilder;
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<ProductDto[]> l = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                ProductDto[].class
        );

        List<Product> answer = new ArrayList<>();

        for (ProductDto productDto: l.getBody()) {
            Product product = new Product();
            product.setId(productDto.getId());
            product.setTitle(productDto.getTitle());
            product.setPrice(productDto.getPrice());
            Category category = new Category();
            category.setName(productDto.getCategory());
            product.setCategory(category);
            product.setImageUrl(productDto.getImage());
            answer.add(product);
        }
//        for (Object obj: l.getBody()) {
//
//            HashMap<String, Object> hm = (HashMap<String, Object>) obj;
//
//            Product product = new Product();
//            product.setId(Long.valueOf((Integer) hm.get("id")));
//            product.setTitle((String) hm.get("title"));
//            product.setPrice(Double.valueOf(hm.get("price").toString()));
//            Category category = new Category();
//            category.setName((String) hm.get("category"));
//            product.setCategory(category);
//            product.setImageUrl((String) hm.get("image"));
//            answer.add(product);
//        }

        return answer;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        return null;
    }

    @Override
    public Product addNewProduct(ProductDto product) {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
