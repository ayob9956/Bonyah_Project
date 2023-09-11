package com.example.Bonyah.Controller;

import com.example.Bonyah.Api.ApiResponse;
import com.example.Bonyah.Models.Complaint;
import com.example.Bonyah.Service.ComplaintService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/complaint")
@RequiredArgsConstructor
public class ComplaintController {
    private final ComplaintService complaintService;
    @GetMapping("/get-all")
    public ResponseEntity getAllComplaint(){
        return ResponseEntity.status(200).body(complaintService.getAllComplaint());
    }
    @PostMapping("/add")
    public ResponseEntity addComplaint(@RequestBody @Valid Complaint complaint){
        complaintService.addComplaint(complaint);
        return ResponseEntity.status(200).body(new ApiResponse("Complaint added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateComplaint(@PathVariable Integer id,@RequestBody @Valid Complaint complaint){
        complaintService.updateComplaint(id,complaint);
        return ResponseEntity.status(200).body(new ApiResponse("Complaint updated"));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComplaint(@PathVariable Integer id){
        complaintService.deleteComplaint(id);
        return ResponseEntity.status(200).body(new ApiResponse("Complaint deleted"));

    }


}
