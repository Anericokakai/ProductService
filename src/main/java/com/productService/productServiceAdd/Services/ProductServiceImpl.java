package com.productService.productServiceAdd.Services;

import com.productService.productServiceAdd.Repository.ProductRepository;
import com.productService.productServiceAdd.models.Products;
import com.productService.productServiceAdd.tdo.ProductRequest;
import com.productService.productServiceAdd.tdo.ProductResponse;
import com.productService.productServiceAdd.tdo.ShopResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RequiredArgsConstructor
@Service

public class ProductServiceImpl  implements ProductService{

private final ProductRepository productRepository;

private final RestTemplate restTemplate;
private final ModelMapper modelMapper;


    @Override
    public String createNewProduct(ProductRequest productRequest) {
        Products newProduct= modelMapper.map(productRequest,Products.class);


        productRepository.save(newProduct);

        return "product was added Successfully";
    }

    @Override
    public List<Products> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductResponse findProductById(int id,int shopId) {


        Products products= productRepository.findById(id).get();
        ProductResponse productResponse=modelMapper.map(products,ProductResponse.class);

      ShopResponse  shopResponse=restTemplate.getForObject("http://localhost:8081/v2/api/shops/{shopId}", ShopResponse.class,shopId);
        productResponse.setShopResponse(shopResponse);
        return  productResponse;
    }
}
