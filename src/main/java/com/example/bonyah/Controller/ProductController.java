package com.example.bonyah.Controller;

import com.example.bonyah.Api.ApiResponse;
import com.example.bonyah.Models.Product;
import com.example.bonyah.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get-all")
    public ResponseEntity getAllProduct(){
        return ResponseEntity.status(200).body(productService.getAllProduct());
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product){
        productService.addProduct(product);
        return ResponseEntity.status(200).body(new ApiResponse("Product added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id,@RequestBody @Valid Product product){
        productService.updateProduct(id,product);
        return ResponseEntity.status(200).body(new ApiResponse("Product updated"));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return ResponseEntity.status(200).body(new ApiResponse("Product deleted"));

    }
}
