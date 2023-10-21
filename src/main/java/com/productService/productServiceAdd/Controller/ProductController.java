package com.productService.productServiceAdd.Controller;


import com.productService.productServiceAdd.Ecxeption.NotFoundException;
import com.productService.productServiceAdd.Services.ProductService;
import com.productService.productServiceAdd.models.Products;
import com.productService.productServiceAdd.tdo.ProductRequest;
import com.productService.productServiceAdd.tdo.ProductResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController

@RequiredArgsConstructor
public class ProductController {

    private final    ProductService productService;


//    ! CREATE A NEW PRODUCT
    @PostMapping("/new")
    public ResponseEntity<?> createNewProduct(@RequestBody @Valid ProductRequest productRequest)  {
     String response=    productService.createNewProduct(productRequest);
         return ResponseEntity.status(201).body(
                 response
         );
    }

//    ! FIND ALL PRODUCTS

    @GetMapping("/all")
    public ResponseEntity<?> findAllProducts() throws NotFoundException {
        List<Products> allProducts= productService.findAllProducts();
        return ResponseEntity.status(200).body(allProducts);
    }

//    FIND BY ID
    @GetMapping("/{productId}")
    public ResponseEntity<?> findProductById(@PathVariable("productId") int id) throws  EntityNotFoundException{

           ProductResponse response= productService.findProductById(id);

         return  ResponseEntity.status(200).body(response);

    }

    @DeleteMapping("/delete/all/{storeNumber}")
    Map<String ,String > deleteAllProductUnderShop(@PathVariable("storeNumber") String  storeNumber) throws EntityNotFoundException {
        Map<String ,String > message=productService.deleteAllProducts(storeNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(message).getBody();
    }




}
