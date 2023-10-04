package dev.sreevidya.productServiceVtSpring.services;

import dev.sreevidya.productServiceVtSpring.dtos.ProductDto;
import dev.sreevidya.productServiceVtSpring.exceptions.NotFoundException;
import dev.sreevidya.productServiceVtSpring.models.Category;
import dev.sreevidya.productServiceVtSpring.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getSingleProduct(Long productId) throws NotFoundException;

    Product addNewProduct(ProductDto product);

    /*
    Product object has only those fields filled which need to be updated.
    Everything else is null
     */
    Product updateProduct(Long productId, Product product);
    // if (product.getImageUrl() != null) {
    //
    // }

    boolean deleteProduct(Long productId);

    List<String> getAllCategories();

    List<Product> getInCategory(String categoryName);


}
