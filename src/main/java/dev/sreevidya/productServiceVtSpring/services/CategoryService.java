package dev.sreevidya.productServiceVtSpring.services;

public interface CategoryService {
    String getAllCategories();

    String getProductsInCategory(Long categoryId);
}
