package com.example.Bonyah.Service;

import com.example.Bonyah.Api.ApiException;
import com.example.Bonyah.Models.Product;
import com.example.Bonyah.Repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    public List<Product>getAllProduct(){
        return productRepo.findAll();
    }
    public void addProduct(Product product){
        productRepo.save(product);
    }


}
