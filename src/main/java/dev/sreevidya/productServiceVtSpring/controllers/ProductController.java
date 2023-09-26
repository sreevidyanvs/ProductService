package dev.sreevidya.productServiceVtSpring.controllers;

import dev.sreevidya.productServiceVtSpring.ProductServiceVtSpringApplication;
import dev.sreevidya.productServiceVtSpring.dtos.ProductDto;
import dev.sreevidya.productServiceVtSpring.models.Product;
import dev.sreevidya.productServiceVtSpring.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String getSingleProduct(@PathVariable("productId") Long productId){
        return "getSingleProduct : " + productId;
    }

    public String limitResults(){
        return "limitResults";
    }

    @PostMapping("/products")
    public String addNewProduct(@RequestBody ProductDto productDto)
    {
        return "addNewProduct" + productDto;
    }

    @PutMapping("/products/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId)
    {
        return "updateProduct";
    }

    @DeleteMapping("/products/{productId}")
    public String DeleteProduct(@PathVariable("productId") Long productId)
    {
        return "DeleteProduct";
    }


}
