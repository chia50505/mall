package com.charlie.mall.service;

import com.charlie.mall.model.Product;
import com.charlie.mall.dto.ProductQueryParams;
import com.charlie.mall.dto.ProductRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(@Valid ProductRequest productRequest);

    void updateProduct (Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Integer countProduct(ProductQueryParams productQueryParams);
}
