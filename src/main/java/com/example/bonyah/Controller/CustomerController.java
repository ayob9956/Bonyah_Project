package com.example.bonyah.Controller;

import com.example.bonyah.Api.ApiResponse;
import com.example.bonyah.DTO.CustomerDTO;
import com.example.bonyah.Models.*;
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
    public ResponseEntity getMyCustomer(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(customerService.getMyCustomer(user.getId()));
    }

    @PutMapping("/update")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal User user, @RequestBody @Valid CustomerDTO customerDTO) {
        customerService.updateCustomer(user.getId(), customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("customer updated"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user) {
        customerService.deleteCustomer(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("customer deleted"));
    }

    @GetMapping("/order/filter-category/{category}")
    public ResponseEntity findProductByCategory(@PathVariable String category) {
        return ResponseEntity.status(200).body(customerService.findProductByCategory(category));
    }

    @GetMapping("/order/filter-category-price/{category}/{price}")
    public ResponseEntity findProductByCategoryAndPrice(@PathVariable String category, @PathVariable Integer price) {
        return ResponseEntity.status(200).body(customerService.findProductByCategoryAndPrice(category, price));
    }

    @GetMapping("/order/filter-price/{price}")
    public ResponseEntity findProductByPrice(@PathVariable Integer price) {

        return ResponseEntity.status(200).body(customerService.findProductByPrice(price));
    }

    @GetMapping("/service/filter-category/{category}")
    public ResponseEntity findServiceByCategory(@PathVariable String category) {

        return ResponseEntity.status(200).body(customerService.findServicesByCategory(category));
    }

    @GetMapping("/service/filter-category-price/{category}/{price}")
    public ResponseEntity findServiceByCategoryAndPrice(@PathVariable String category, @PathVariable Integer price) {
        return ResponseEntity.status(200).body(customerService.findServicesByCategoryAndPrice(category, price));
    }

    @GetMapping("/service/filter-price/{price}")
    public ResponseEntity findServiceByPrice(@PathVariable Integer price) {

        return ResponseEntity.status(200).body(customerService.findServicesByPrice(price));
    }

    @GetMapping("/filter-provider/{name}")
    public ResponseEntity findProviderByName(@PathVariable String name) {

        return ResponseEntity.status(200).body(customerService.findProviderByName(name));
    }

    @GetMapping("/get-orders")
    public ResponseEntity getMyOrders(@AuthenticationPrincipal User user) {

        return ResponseEntity.status(200).body(customerService.getMyOrders(user.getId()));
    }

    @GetMapping("/get-request")
    public ResponseEntity getRequest(@AuthenticationPrincipal User user) {

        return ResponseEntity.status(200).body(customerService.getRequest(user.getId()));
    }

    @PostMapping("/add-order/{product_id}")
    public ResponseEntity sendOrder(@AuthenticationPrincipal User user, @PathVariable Integer
            product_id, @RequestBody @Valid Orders orders) {
        customerService.sendOrder(user.getId(), product_id, orders);
        return ResponseEntity.status(200).body(new ApiResponse("order added"));
    }

    @PostMapping("/add-request/{serviceId}")
    public ResponseEntity sendRequest(@AuthenticationPrincipal User user, @PathVariable Integer
            serviceId, @RequestBody @Valid Request request) {
        customerService.sendRequest(user.getId(), serviceId, request);
        return ResponseEntity.status(200).body(new ApiResponse("request added"));
    }

    @PutMapping("/update-order/{order_id}")
    public ResponseEntity UpdateOrder(@AuthenticationPrincipal User user, @PathVariable Integer
            order_id, @RequestBody @Valid Orders orders) {
        customerService.UpdateOrder(user.getId(), order_id, orders);
        return ResponseEntity.status(200).body(new ApiResponse("order updated"));
    }

    @PutMapping("/update-request/{request_id}")
    public ResponseEntity UpdateRequest(@AuthenticationPrincipal User user, @PathVariable Integer
            request_id, @RequestBody @Valid Request request) {
        customerService.UpdateRequest(user.getId(), request_id, request);
        return ResponseEntity.status(200).body(new ApiResponse("request updated"));
    }

    @DeleteMapping("/delete-order/{order_id}")
    public ResponseEntity deleteOrders(@AuthenticationPrincipal User user, @PathVariable Integer order_id) {
        customerService.deleteOrders(user.getId(), order_id);
        return ResponseEntity.status(200).body(new ApiResponse("order deleted"));
    }

    @DeleteMapping("/delete-request/{request_id}")
    public ResponseEntity deleteRequest(@AuthenticationPrincipal User user, @PathVariable Integer request_id) {
        customerService.deleteRequest(user.getId(), request_id);
        return ResponseEntity.status(200).body(new ApiResponse("request deleted"));
    }

    @PutMapping("/pay-order/{id}")
    public ResponseEntity payOrderInvoice(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        customerService.payOrderInvoice(user.getId(), id);
        return ResponseEntity.status(200).body(new ApiResponse("payment went successfully"));
    }

    @PutMapping("/pay-request/{id}")
    public ResponseEntity payRequestInvoice(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        customerService.payRequestInvoice(user.getId(), id);
        return ResponseEntity.status(200).body(new ApiResponse("payment went successfully"));
    }

    @PutMapping("/cancel-invoice/{id}")
    public ResponseEntity cancelInvoice(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        customerService.cancelInvoice(user.getId(), id);
        return ResponseEntity.status(200).body(new ApiResponse("payment went successfully"));
    }

    @GetMapping("/my-invoices")
    public ResponseEntity getMyInvoices(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(customerService.getMyInvoices(user.getId()));
    }

    @PostMapping("/send-complaint")
    public ResponseEntity sendComplaint(@AuthenticationPrincipal User user, @RequestBody @Valid Complaint complaint) {
        customerService.sendComplaint(user.getId(), complaint);
        return ResponseEntity.status(200).body(new ApiResponse("complaint went successfully"));
    }


}