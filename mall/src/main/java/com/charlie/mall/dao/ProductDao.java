package com.charlie.mall.dao;

import com.charlie.mall.model.Product;
import com.charlie.mall.dto.ProductQueryParams;
import com.charlie.mall.dto.ProductRequest;

import java.util.List;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Integer countProduct(ProductQueryParams productQueryParams);
}
