package com.charlie.mall.controller;

import com.charlie.mall.constant.ProductCategory;
import com.charlie.mall.dao.impl.ProductDaoImpl;
import com.charlie.mall.model.Product;
import com.charlie.mall.service.ProductService;
import com.charlie.mall.util.Page;
import com.charlie.mall.dto.ProductQueryParams;
import com.charlie.mall.dto.ProductRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    private ProductDaoImpl productDaoImpl;



    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);

        if(product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Integer productId = productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,@RequestBody @Valid ProductRequest productRequest){

        if(productService.getProductById(productId) ==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        productService.updateProduct(productId,productRequest);

        Product updateProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){

        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(@RequestParam(required = false) ProductCategory category,
                                              @RequestParam(required = false) String search,
                                              @RequestParam(defaultValue = "created_date") String orderBy,
                                              @RequestParam(defaultValue = "desc") String sort,
                                              @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
                                              @RequestParam(defaultValue = "0") @Min(0) Integer offset){
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);


        List<Product> productList = productService.getProducts(productQueryParams);

        Integer total = productService.countProduct(productQueryParams);

        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productList);

        return ResponseEntity.status(HttpStatus.OK).body(page);

    }




}
