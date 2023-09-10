package com.example.Bonyah.Service;


import com.example.Bonyah.Api.ApiException;
import com.example.Bonyah.Models.Complaint;
import com.example.Bonyah.Models.Provider;
import com.example.Bonyah.Models.User;
import com.example.Bonyah.Repository.AuthRepo;
import com.example.Bonyah.Repository.ComplaintRepo;
import com.example.Bonyah.Repository.ProviderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AuthRepo authRepo;
    private final ProviderRepo providerRepo;
    private final ComplaintRepo complaintRepo;

    public List<User> getAllUsers() {
        return authRepo.findAll();
    }

    public List<User> getUsersByRole(String role) {
        return authRepo.findAllByRole(role);
    }

    public User getOneUser(Integer id) {

        User user = authRepo.findUserById(id);

        if (user == null) {
            throw new ApiException("user not found");
        }
        return user;
    }

    public List<User> getAllWaitingList() {
        return authRepo.getAllWaitingList();
    }


    public void confirmProvider(Integer id) {
        Provider provider = providerRepo.findProviderById(id);

        if (provider == null) {
            throw new ApiException("user not found");
        }

        provider.setStatus("confirmed");

        providerRepo.save(provider);
    }


    public void rejectProvider(Integer id) {
        Provider provider = providerRepo.findProviderById(id);

        if (provider == null) {
            throw new ApiException("user not found");
        }

        provider.setStatus("reject");

        providerRepo.save(provider);
    }

    public void deleteUser(Integer id) {
        User user = authRepo.findUserById(id);

        if (user == null) {
            throw new ApiException("user not found");
        }

        authRepo.delete(user);
    }


    public List<Complaint> getAllComplaint() {
        return complaintRepo.findAll();
    }

    public void finishComplaint(Integer id) {
        Complaint complaint = complaintRepo.findComplaintById(id);

        if (complaint == null) {
            throw new ApiException("complaint not found");
        }

        complaint.setStatus("finished");
        complaintRepo.save(complaint);
    }

    public void openComplaint(Integer id) {
        Complaint complaint = complaintRepo.findComplaintById(id);

        if (complaint == null) {
            throw new ApiException("complaint not found");
        }

        complaint.setStatus("opened");
        complaintRepo.save(complaint);
    }

    public List<Complaint> getWaitingComplaint() {
        return complaintRepo.getAllWaitingComplaints();
    }

}

