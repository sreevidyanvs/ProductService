package dev.sreevidya.productServiceVtSpring.dtos;

import dev.sreevidya.productServiceVtSpring.models.Product;

import java.util.List;

public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private List<Product> products;
}
