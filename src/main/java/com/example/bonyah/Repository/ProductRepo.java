package com.example.bonyah.Repository;

import com.example.bonyah.Models.Product;
import com.example.bonyah.Models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {


    Product findProductById(Integer id);
    List<Product> findProductsByCategory(String category);

    @Query("select p from Product p where p.price <= ?1")
    List<Product>findProductsByPrice(Integer price);

    @Query("select p from Product p where p.category = ?1 and p.price <= ?2" )
    List<Product> findProductsByCategoryAndPrice(String category,Integer price);
    List<Product>findProductsByProvider(Provider provider);


}
