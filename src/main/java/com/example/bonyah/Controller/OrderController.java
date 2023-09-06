package com.example.bonyah.Controller;

import com.example.bonyah.Api.ApiResponse;
import com.example.bonyah.Models.Orders;
import com.example.bonyah.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/get-all")
    public ResponseEntity getAllOrder() {
        return ResponseEntity.status(200).body(orderService.getAllOrder());
    }

    @PostMapping("/add")
    public ResponseEntity addOrder(@RequestBody @Valid Orders order) {
        orderService.addOrder(order);
        return ResponseEntity.status(200).body(new ApiResponse("order added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable Integer id, @RequestBody @Valid Orders order) {
        orderService.updateOrder(id, order);
        return ResponseEntity.status(200).body(new ApiResponse("order updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.status(200).body(new ApiResponse("order deleted"));
    }

}
