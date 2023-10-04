package dev.sreevidya.productServiceVtSpring.controllers;

import dev.sreevidya.productServiceVtSpring.ProductServiceVtSpringApplication;
import dev.sreevidya.productServiceVtSpring.dtos.ProductDto;
import dev.sreevidya.productServiceVtSpring.exceptions.NotFoundException;
import dev.sreevidya.productServiceVtSpring.models.Category;
import dev.sreevidya.productServiceVtSpring.models.Product;
import dev.sreevidya.productServiceVtSpring.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {


    private ProductService productService;
    public ProductController( ProductService productService) {
        this.productService = productService;
    }


    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add(
                "auth-token", "noaccess4uheyhey"
        );


        Optional<Product> productOptional = productService.getSingleProduct(productId);
        Product product = productOptional.get();

        if (productOptional.isEmpty()) {
            throw new NotFoundException("No Product with product id: " + productId);
        }

        ResponseEntity<Product> response = new ResponseEntity(
                productService.getSingleProduct(productId),
                headers,
                HttpStatus.NOT_FOUND
        );

        return response;
    }

    public String limitResults(){
        return "limitResults";
    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto productDto)
    {
        Product newProduct = productService.addNewProduct(
                productDto
        );

        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId,@RequestBody ProductDto productDto)
    {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        Product newProduct = productService.updateProduct(productId,product);

        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public boolean DeleteProduct(@PathVariable("productId") Long productId)
    {
        return productService.deleteProduct(productId);
    }

    @GetMapping("/categories")
    public List<String> getAllCategories(){
        return productService.getAllCategories();
    }

    @GetMapping("/category/{categoryName}")
    public List<Product> getProductsInCategory(@PathVariable String categoryName) {
        return productService.getInCategory(categoryName);
    }

}
