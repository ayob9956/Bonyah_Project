package com.example.bonyah.Controller;


import com.example.bonyah.Api.ApiResponse;
import com.example.bonyah.DTO.CustomerDTO;
import com.example.bonyah.DTO.ProviderDTO;
import com.example.bonyah.Service.AuthServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServices authServices;


    @PostMapping("/add/customer")
    public ResponseEntity registerCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        authServices.customerRegister(customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("customer added"));
    }


    @PostMapping("/add/provider")
    public ResponseEntity registerProvider(@RequestBody @Valid ProviderDTO providerDTO) {
        authServices.providerRegister(providerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("provider added added"));
    }
}
