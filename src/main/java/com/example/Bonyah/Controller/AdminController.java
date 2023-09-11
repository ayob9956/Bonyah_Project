package com.example.Bonyah.Controller;


import com.example.Bonyah.Api.ApiResponse;
import com.example.Bonyah.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    @GetMapping("/get-all-users")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(adminService.getAllUsers());
    }

    @GetMapping("/get-users-role/{role}")
    public ResponseEntity getUsersByRole(@PathVariable String role) {
        return ResponseEntity.status(200).body(adminService.getUsersByRole(role));
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity getOneUser(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(adminService.getOneUser(id));
    }

    @GetMapping("/get-waiting-list")
    public ResponseEntity getAllWaitingList() {
        return ResponseEntity.status(200).body(adminService.getAllWaitingList());
    }

    @PutMapping("/confirm-provider/{id}")
    public ResponseEntity confirmProvider(@PathVariable Integer id) {
        adminService.confirmProvider(id);
        return ResponseEntity.status(200).body(new ApiResponse("provider confirmed"));
    }

    @PutMapping("/reject-provider/{id}")
    public ResponseEntity rejectProvider(@PathVariable Integer id) {
        adminService.rejectProvider(id);
        return ResponseEntity.status(200).body(new ApiResponse("provider rejected"));
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        adminService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted"));
    }

    @GetMapping("/get-complaints")
    public ResponseEntity getAllComplaints() {
        return ResponseEntity.status(200).body(adminService.getAllComplaint());
    }

    @PutMapping("/finish-complaint/{id}")
    public ResponseEntity finishComplaint(@PathVariable Integer id) {
        adminService.finishComplaint(id);
        return ResponseEntity.status(200).body(new ApiResponse("complaint finished"));
    }

    @PutMapping("/open-complaint/{id}")
    public ResponseEntity openComplaint(@PathVariable Integer id) {
        adminService.openComplaint(id);
        return ResponseEntity.status(200).body(new ApiResponse("complaint opened"));
    }

    @GetMapping("/get-waiting-complaint")
    public ResponseEntity getWaitingComplaint() {
        return ResponseEntity.status(200).body(adminService.getWaitingComplaint());
    }
}
