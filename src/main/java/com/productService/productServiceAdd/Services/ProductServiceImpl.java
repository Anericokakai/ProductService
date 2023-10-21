package com.productService.productServiceAdd.Services;

import com.productService.productServiceAdd.OpenFeignClient.ShopClient;
import com.productService.productServiceAdd.Repository.ProductRepository;
import com.productService.productServiceAdd.models.Products;
import com.productService.productServiceAdd.tdo.ProductRequest;
import com.productService.productServiceAdd.tdo.ProductResponse;
import com.productService.productServiceAdd.tdo.ShopResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Service

public class ProductServiceImpl  implements ProductService{

private final ProductRepository productRepository;

private final RestTemplate restTemplate;

private final ModelMapper modelMapper;
//@Value("${shopsservice.base.url}")
//private String adressBaseURL;


    private  final WebClient webClient;
    @Autowired
    private  final ShopClient shopClient;

    private  final LoadBalancerClient loadBalancerClient;

//private  final DiscoveryClient discoveryClient;

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
    public ProductResponse findProductById(int id) {


        Products products= productRepository.findById(id).get();
        ProductResponse productResponse=modelMapper.map(products,ProductResponse.class);

//        ! USING THE WEBCLIENT
//
//        ShopResponse shopResponse=webClient
//                .get()
//                .uri("/shops/"+shopId)
//                .retrieve()
//                .bodyToMono(ShopResponse.class)
//                .block();
//        productResponse.setShopResponse(shopResponse);

//! USING REST TEMPLATE

//        List<ServiceInstance> instances= discoveryClient.getInstances("shop-service");
//
//        ServiceInstance serviceInstance= instances.get(0);
//


    ServiceInstance serviceInstance=    loadBalancerClient.choose("shop-service");
          String uri= serviceInstance.getUri().toString();
          String contextPath=serviceInstance.getMetadata().get("configPath");
        System.out.println("URLSerivce : "+uri);
        System.out.println("URLContextPath : "+contextPath);

//   ShopResponse  shopResponse=restTemplate.getForObject(uri+contextPath+"/{shopId}", ShopResponse.class,shopId);
//        productResponse.setShopResponse(shopResponse);

//        ShopResponse  shopResponse=restTemplate.getForObject("http://SHOP-SERVICE"+contextPath+"/{shopId}", ShopResponse.class,shopId);
//        productResponse.setShopResponse(shopResponse);

//        ! USING OPEN FEIGN


        ShopResponse shopResponse=shopClient.FindShopById(products.getStoreNumber());
        productResponse.setShopResponse(shopResponse);


        return  productResponse;
    }

    @Override
    public Map<String, String> deleteAllProducts(String storeNumber) {


   productRepository.deleteAllByStoreNumber(storeNumber);

    Map<String,String> message=new HashMap<>();

  message.put("productMessage","products deleted succesfully");
        return message;
    }


}
