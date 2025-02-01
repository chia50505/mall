package com.charlie.mall.service.Impl;

import com.charlie.mall.dao.ProductDao;
import com.charlie.mall.model.Product;
import com.charlie.mall.service.ProductService;
import com.charlie.mall.dto.ProductQueryParams;
import com.charlie.mall.dto.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {

        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {

        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {

        productDao.updateProduct(productId, productRequest);
    }

    @Override
    public void deleteProductById(Integer productId) {

        productDao.deleteProductById(productId);
    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {

        return productDao.getProducts(productQueryParams);
    }

    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {

        return productDao.countProduct(productQueryParams);
    }


}
