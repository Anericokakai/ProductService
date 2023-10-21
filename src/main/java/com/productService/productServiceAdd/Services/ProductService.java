package com.productService.productServiceAdd.Services;

import com.productService.productServiceAdd.Ecxeption.NotFoundException;
import com.productService.productServiceAdd.Repository.ProductRepository;
import com.productService.productServiceAdd.models.Products;
import com.productService.productServiceAdd.tdo.ProductRequest;
import com.productService.productServiceAdd.tdo.ProductResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService  {


    String createNewProduct(ProductRequest productRequest);

    List<Products> findAllProducts() throws NotFoundException;
    ProductResponse findProductById(int id);
    Map<String,String> deleteAllProducts(String storeNumber);
}
