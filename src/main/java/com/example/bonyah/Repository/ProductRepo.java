package com.example.bonyah.Repository;

import com.example.bonyah.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    Product findProductById(Integer id);



}
