package com.productService.productServiceAdd.Services;

import com.productService.productServiceAdd.Repository.ProductRepository;
import com.productService.productServiceAdd.models.Products;
import com.productService.productServiceAdd.tdo.ProductRequest;
import com.productService.productServiceAdd.tdo.ProductResponse;

import java.util.List;

public interface ProductService  {


    public ProductResponse createNewProduct(ProductRequest productRequest);

    public List<Products> findAllProducts();
}
