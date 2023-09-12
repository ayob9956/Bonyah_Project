package com.example.Bonyah.Controller;


import com.example.Bonyah.DTO.ProviderDTO;
import com.example.Bonyah.DTO.RejectRequestDTO;
import com.example.Bonyah.Models.Product;
import com.example.Bonyah.Models.Provider;
import com.example.Bonyah.Models.Service;
import com.example.Bonyah.Models.User;
import com.example.Bonyah.Service.ProviderService;
import com.example.Bonyah.Api.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/provider")
@RequiredArgsConstructor
public class ProviderController {
    private final ProviderService providerService;

    @GetMapping("/my-info")
    public ResponseEntity getProviderInfo(@AuthenticationPrincipal User user) {

        return ResponseEntity.status(200).body(providerService.getProviderInfo(user));
    }

    @PutMapping("/update")
    public ResponseEntity updateProvider(@AuthenticationPrincipal User user, @RequestBody @Valid ProviderDTO provider) {
        providerService.updateUser(user, provider);
        return ResponseEntity.status(200).body("Provider updated successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteProvider(@AuthenticationPrincipal User user) {

        providerService.deleteProvider(user.getId());
        return ResponseEntity.status(200).body("Provider deleted successfully");
    }

    @PostMapping("/add/product")
    public ResponseEntity addproducts(@AuthenticationPrincipal User user, @RequestBody Product product) {

        providerService.addProduct(user, product);
        return ResponseEntity.status(200).body("Product added successfully");

    }

    @PutMapping("/update/product/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody @Valid Product product, @AuthenticationPrincipal User user) {
        providerService.updateProduct(id, product, user);
        return ResponseEntity.status(200).body(new ApiResponse("Product updated"));

    }

    @DeleteMapping("/delete/product/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        providerService.deleteProduct(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("Product deleted"));

    }

    @GetMapping("/my-products")
    public ResponseEntity myProducts(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(providerService.myProducts(user.getId()));
    }


    @GetMapping("/my-services")
    public ResponseEntity myServices(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(providerService.myServices(user.getId()));
    }

    @PostMapping("/add/service")
    public ResponseEntity addproducts(@AuthenticationPrincipal User user, @RequestBody @Valid Service service) {

        providerService.addService(user, service);
        return ResponseEntity.status(200).body("Product added successfully");

    }

    @GetMapping("/get/orders")
    public ResponseEntity getOrdersbyProvider(@AuthenticationPrincipal User user) {

        return ResponseEntity.status(200).body(providerService.getMyOrders(user));
    }

    @PutMapping("/update/service/{id}")
    public ResponseEntity updateService(@PathVariable Integer id, @RequestBody @Valid Service service, @AuthenticationPrincipal User user) {
        providerService.updateService(id, service, user);
        return ResponseEntity.status(200).body(new ApiResponse("Service updated"));

    }

    @DeleteMapping("/delete/service/{id}")
    public ResponseEntity deleteService(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        providerService.deleteService(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("Service deleted"));

    }

    @GetMapping("/get/requests")
    public ResponseEntity getRequestProvider(@AuthenticationPrincipal User user) {

        return ResponseEntity.status(200).body(providerService.getRequestByProvider(user));
    }


    @GetMapping("/confirm/order/{id}")
    public ResponseEntity getOrdersByProvider(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        providerService.confirmOrders(user, id);
        return ResponseEntity.status(200).body("order is confirmed");
    }


    @GetMapping("/confirm/request/{id}")
    public ResponseEntity confirmRequest(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        providerService.confirmRequest(user, id);
        return ResponseEntity.status(200).body("Request is confirmed");
    }


    @GetMapping("/reject/order/{id}")
    public ResponseEntity rejectOrder(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        providerService.rejectOrders(user, id);
        return ResponseEntity.status(200).body("order is reject");
    }

    @GetMapping("/reject/request/{id}")
    public ResponseEntity rejectRequest(@AuthenticationPrincipal User user, @PathVariable Integer id, @RequestBody RejectRequestDTO rejectRequestDTO) {
        providerService.rejectRequest(user, id, rejectRequestDTO);
        return ResponseEntity.status(200).body("Request is reject");
    }
}

