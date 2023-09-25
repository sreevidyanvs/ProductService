package dev.sreevidya.productServiceVtSpring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @GetMapping("/categories")
    public String getAllCategories()
    {
        return "getAllCategories";
    }

    public String getProductsInCategory()
    {
        return "getProductsInCategory";
    }


}
