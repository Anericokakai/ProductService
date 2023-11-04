package com.productService.productServiceAdd.OpenFeignClient;

import com.productService.productServiceAdd.tdo.ShopResponse;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "shop-service",path = "/v2/api/shops/home")

public interface ShopClient {
    @GetMapping("/{storeNumber}")
    ShopResponse FindShopByStoreNumber(@PathVariable("storeNumber") String storeNumber);





}
