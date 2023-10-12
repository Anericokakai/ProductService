package com.productService.productServiceAdd.Repository;

import com.productService.productServiceAdd.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Products,Integer> {

  Optional<Products> findByProductName(String prosuctName);
}
