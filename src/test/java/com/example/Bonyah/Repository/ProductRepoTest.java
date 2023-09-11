package com.example.Bonyah.Repository;

import com.example.Bonyah.Models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepoTest {
    @Autowired
    ProductRepo productRepo;
    Product product1;
    Product product2;
    Product product3;
    List<Product> products;

    @BeforeEach
    void setUp() {
        product1 =new Product(1,"Sabic",3000,"iron","Reinforcing steel",300,null,null,null);
        product2 =new Product(2,"Rajhi Steel",3500,"iron","Reinforcing steel",400,null,null,null);
        product3 =new Product(2,"yamama",15,"Cement","Cement bags",60,null,null,null);

    }

    @Test
    void findProductById() {
        productRepo.findProductById(product1.getId());
    }

    @Test
    void findProductsByCategory() {
        productRepo.findProductsByCategory("iron");
    }

    @Test
    void findProductsByPrice() {
        productRepo.findProductsByPrice(3500);
    }
}