package com.productService.productServiceAdd.tdo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class ShopResponse {


    private String shopName;

    private  String shopLocation;

    private String shopContact;
}
