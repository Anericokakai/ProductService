package com.productService.productServiceAdd.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;
    @Column(name = "product_name")

    private String productName;
    @Column(name = "product_desc")

    private String productDesc;

    @Column(name = "price")

    private Integer price;
    @Column(name = "store_number")

    private String storeNumber ;

}
