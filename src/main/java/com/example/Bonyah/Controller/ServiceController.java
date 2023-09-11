package com.example.Bonyah.Controller;

import com.example.Bonyah.Api.ApiResponse;
import com.example.Bonyah.Models.Service;
import com.example.Bonyah.Service.ServiceServices;
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

}
