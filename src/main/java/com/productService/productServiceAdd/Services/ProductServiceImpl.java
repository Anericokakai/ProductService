package com.productService.productServiceAdd.Services;

import com.productService.productServiceAdd.Repository.ProductRepository;
import com.productService.productServiceAdd.models.Products;
import com.productService.productServiceAdd.tdo.ProductRequest;
import com.productService.productServiceAdd.tdo.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service

public class ProductServiceImpl  implements ProductService{

private final ProductRepository productRepository;


    @Override
    public ProductResponse createNewProduct(ProductRequest productRequest) {
        Products newProduct=Products.builder()

                .productName(productRequest.getProductName())
                .productDesc(productRequest.getProductDesc())
                .price(productRequest.getPrice())
                .shopid(productRequest.getShopId())
                .build();


        productRepository.save(newProduct);

        return ProductResponse.builder()
                .id(newProduct.getId())
                .productDesc(newProduct.getProductDesc())
                .productName(newProduct.getProductName())
                .price(newProduct.getPrice())
                .shopId(newProduct.getShopid())

                .build();
    }

    @Override
    public List<Products> findAllProducts() {
        return productRepository.findAll();
    }
}
