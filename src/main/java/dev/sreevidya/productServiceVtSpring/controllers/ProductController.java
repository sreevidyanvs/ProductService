package dev.sreevidya.productServiceVtSpring.controllers;

import dev.sreevidya.productServiceVtSpring.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @GetMapping("/products")
    public String getAllProducts(){
        return "getAllProducts";
    }

    @GetMapping("/products/{productId}")
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
