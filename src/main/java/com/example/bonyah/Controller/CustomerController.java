package com.example.bonyah.Controller;

import com.example.bonyah.Api.ApiResponse;
import com.example.bonyah.DTO.CustomerDTO;
import com.example.bonyah.Models.Orders;
import com.example.bonyah.Models.User;
import com.example.bonyah.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/get")
    public ResponseEntity getMyCustomer(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(customerService.getMyCustomer(user.getId()));
    }
    @PutMapping("/update")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal User user, @RequestBody @Valid CustomerDTO customerDTO){
        customerService.updateCustomer(user.getId(),customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("customer updated"));
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user){
        customerService.deleteCustomer(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("customer deleted"));
    }
    @GetMapping("/order/filter-category/{category}")
    public ResponseEntity findProductByCategory(@PathVariable String category){

        return ResponseEntity.status(200).body(customerService.findProductByCategory(category));
    }
    @GetMapping("/order/filter-category-price/{category}/{price}")
    public ResponseEntity findProductByCategoryAndPrice(@PathVariable String category,@PathVariable Integer price){
        return ResponseEntity.status(200).body(customerService.findProductByCategoryAndPrice(category,price));
    }
    @GetMapping("/order/filter-price/{price}")
    public ResponseEntity findProductByPrice(@PathVariable Integer price){

        return ResponseEntity.status(200).body(customerService.findProductByPrice(price));
    }
    @GetMapping("/service/filter-category/{category}")
    public ResponseEntity findServiceByCategory(@PathVariable String category){

        return ResponseEntity.status(200).body(customerService.findServicesByCategory(category));
    }
    @GetMapping("/service/filter-category-price/{category}/{price}")
    public ResponseEntity findServiceByCategoryAndPrice(@PathVariable String category,@PathVariable Integer price){
        return ResponseEntity.status(200).body(customerService.findServicesByCategoryAndPrice(category,price));
    }
    @GetMapping("/service/filter-price/{price}")
    public ResponseEntity findServiceByPrice(@PathVariable Integer price){

        return ResponseEntity.status(200).body(customerService.findServicesByPrice(price));
    }
    @GetMapping("/filter-provider/{name}")
    public ResponseEntity findProviderByName(@PathVariable String name){

        return ResponseEntity.status(200).body(customerService.findProviderByName(name));
    }






}
