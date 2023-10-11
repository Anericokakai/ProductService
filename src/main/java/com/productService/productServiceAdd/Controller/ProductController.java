package com.productService.productServiceAdd.Controller;


import com.productService.productServiceAdd.Services.ProductService;
import com.productService.productServiceAdd.models.Products;
import com.productService.productServiceAdd.tdo.ProductRequest;
import com.productService.productServiceAdd.tdo.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

@RequiredArgsConstructor
public class ProductController {

    private final    ProductService productService;


//    ! CREATE A NEW PRODUCT
    @PostMapping("/new")
    public ResponseEntity<?> createNewProduct(@RequestBody @Valid ProductRequest productRequest,BindingResult bindingResult){

//        if there are errors
        if (productRequest.getProductDesc().isEmpty()||productRequest.getProductName().isEmpty()||productRequest.getPrice()==null||productRequest.getShopId()==null) {
            HashMap<String,String> error= new HashMap<>();
            error.put("errorMessage","all fields are requires");
            return ResponseEntity.badRequest().body(error);

        }

     ProductResponse response=    productService.createNewProduct(productRequest);
         return ResponseEntity.status(201).body(
                 response
         );
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllProducts() {
        List<Products> allProducts= productService.findAllProducts();
        return ResponseEntity.status(200).body(allProducts);
    }




}
