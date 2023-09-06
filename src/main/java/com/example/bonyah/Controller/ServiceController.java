package com.example.bonyah.Controller;

import com.example.bonyah.Api.ApiResponse;
import com.example.bonyah.Models.Invoice;
import com.example.bonyah.Models.Service;
import com.example.bonyah.Service.ServiceServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/service")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceServices serviceServices;

    @GetMapping("/get-all")
    public ResponseEntity getAllService(){
        return ResponseEntity.status(200).body(serviceServices.getAllService());
    }

    @PostMapping("/add")
    public ResponseEntity addService(@RequestBody @Valid Service service){
        serviceServices.addService(service);
        return ResponseEntity.status(200).body(new ApiResponse("Service added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateService(@PathVariable Integer id,@RequestBody @Valid Service service){
        serviceServices.updateService(id,service);
        return ResponseEntity.status(200).body(new ApiResponse("Service updated"));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteService(@PathVariable Integer id){
        serviceServices.deleteService(id);
        return ResponseEntity.status(200).body(new ApiResponse("Service deleted"));

    }
}
