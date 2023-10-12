package com.productService.productServiceAdd.tdo;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProductResponse {


    private Long id;
    private String productName;

    private String productDesc;

    private Integer price;
    private Integer shopId;
    private ShopResponse shopResponse;


}
