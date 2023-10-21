package com.productService.productServiceAdd.Repository;

import com.productService.productServiceAdd.models.Products;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Products,Integer> {

  Optional<Products> findByProductName(String prosuctName);

  @Modifying
  @Transactional
  @Query(" DELETE FROM Products p WHERE p.storeNumber =:storeNumber ")
  void deleteAllByStoreNumber(String storeNumber);
}
