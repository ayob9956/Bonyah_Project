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

    public void updateProduct(Integer id,Product product){
        Product product1=productRepo.findProductById(id);
        if (product1==null){
            throw new ApiException("id not founded");
        }
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setCategory(product.getCategory());
        product1.setDescription(product.getDescription());
        product1.setStock(product.getStock());
        productRepo.save(product1);

    }
    public void deleteProduct(Integer id){
        Product product = productRepo.findProductById(id);
        if (product==null){
            throw new ApiException("id not founded");

        }
        productRepo.delete(product);
    }
}
