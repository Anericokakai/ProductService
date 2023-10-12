package com.productService.productServiceAdd.Services;

import com.productService.productServiceAdd.Repository.ProductRepository;
import com.productService.productServiceAdd.models.Products;
import com.productService.productServiceAdd.tdo.ProductRequest;
import com.productService.productServiceAdd.tdo.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService  {


    String createNewProduct(ProductRequest productRequest);

    List<Products> findAllProducts();
    ProductResponse findProductById(int id,int shopId);
}
