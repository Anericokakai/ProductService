package com.productService.productServiceAdd.tdo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = "the product name field can not be blank")
    private String productName;
    @NotBlank(message = "the product description  field can not be blank")
    private String productDesc;

    private Integer price;
    @NotBlank(message = "store number must be a valid store number and not blank")
    private String storeNumber;
}
