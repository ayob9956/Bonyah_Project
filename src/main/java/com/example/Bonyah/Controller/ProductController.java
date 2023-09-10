package com.example.Bonyah.Controller;

import com.example.Bonyah.Api.ApiResponse;
import com.example.Bonyah.Models.Product;
import com.example.Bonyah.Service.ProductService;
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


}
