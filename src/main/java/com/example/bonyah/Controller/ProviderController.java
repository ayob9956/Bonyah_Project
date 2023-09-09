package com.example.bonyah.Controller;


import com.example.bonyah.Models.Product;
import com.example.bonyah.Models.Provider;
import com.example.bonyah.Models.Service;
import com.example.bonyah.Models.User;
import com.example.bonyah.Service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/provider")
@RequiredArgsConstructor
public class ProviderController {
    private final ProviderService providerService;

    @GetMapping("/get")
    public ResponseEntity getProvider(@AuthenticationPrincipal User user) {

        return ResponseEntity.status(200).body(providerService.getProviderInfrometions(user));
    }

    @PutMapping("/update")
    public ResponseEntity updateProvider(@AuthenticationPrincipal User user,@RequestBody Provider provider){
        providerService.updateUser(user,provider);
        return ResponseEntity.status(200).body("Provider updated successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteProvider(@AuthenticationPrincipal User user){

        providerService.deleteProvider(user.getId());
        return ResponseEntity.status(200).body("Provider deleted successfully");
    }

    @PostMapping("/add/product")
    public ResponseEntity addproducts(@AuthenticationPrincipal User user,@RequestBody Product product){

        providerService.addProduct(user, product);
        return ResponseEntity.status(200).body("Product added successfully");

    }

    @PostMapping("/add/service")
    public ResponseEntity addproducts(@AuthenticationPrincipal User user, @RequestBody Service service){

        providerService.addService(user, service);
        return ResponseEntity.status(200).body("Product added successfully");

    }
    @GetMapping("/get/orders")
    public ResponseEntity getOrdersbyProvider(@AuthenticationPrincipal User user){

        return ResponseEntity.status(200).body( providerService.getMyOrders(user));
    }


    @GetMapping("/get/requests")
    public ResponseEntity getRequestProvider(@AuthenticationPrincipal User user){

        return ResponseEntity.status(200).body( providerService.getRequestByProvider(user));
    }

    @GetMapping("/confirm/order/{id}")
    public ResponseEntity getOrdersByProvider(@AuthenticationPrincipal User user,@PathVariable Integer id){
        providerService.confirmOrders(user,id);
        return ResponseEntity.status(200).body("order is confirmed" );
    }


    @GetMapping("/confirm/request/{id}")
    public ResponseEntity confirmRequest(@AuthenticationPrincipal User user,@PathVariable Integer id){
        providerService.confirmRequest(user,id);
        return ResponseEntity.status(200).body("Request is confirmed" );
    }


    @GetMapping("/reject/order/{id}")
    public ResponseEntity rejectOrder(@AuthenticationPrincipal User user,@PathVariable Integer id){
        providerService.rejectOrders(user,id);
        return ResponseEntity.status(200).body("order is reject");
    }

    @GetMapping("/reject/request/{id}")
    public ResponseEntity rejectRequest(@AuthenticationPrincipal User user,@PathVariable Integer id){
        providerService.rejectRequest(user,id);
        return ResponseEntity.status(200).body("Request is reject");
    }
}

