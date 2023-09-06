package com.example.bonyah.Controller;

import com.example.bonyah.Api.ApiResponse;
import com.example.bonyah.Models.Request;
import com.example.bonyah.Service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @GetMapping("/get-all")
    public ResponseEntity getAllRequest(){
        return ResponseEntity.status(200).body(requestService.getAllRequest());
    }

    @PostMapping("/add")
    public ResponseEntity addRequest(@RequestBody @Valid Request request){
        requestService.addRequest(request);
        return ResponseEntity.status(200).body(new ApiResponse("Request added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateRequest(@PathVariable Integer id,@RequestBody @Valid Request request){
            requestService.updateRequest(id,request);
        return ResponseEntity.status(200).body(new ApiResponse("Request updated"));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRequest(@PathVariable Integer id){
        requestService.deleteRequest(id);
        return ResponseEntity.status(200).body(new ApiResponse("Request deleted"));

    }
}
