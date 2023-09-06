package com.example.bonyah.Service;

import com.example.bonyah.Api.ApiException;
import com.example.bonyah.Models.Complaint;
import com.example.bonyah.Repository.ComplaintRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {
    private final ComplaintRepo complaintRepo;
    public List<Complaint>getAllComplaint(){
        return complaintRepo.findAll();
    }
    public void addComplaint(Complaint complaint){
        complaintRepo.save(complaint);
    }
    public void updateComplaint(Integer id,Complaint complaint){
        Complaint complaint1 =complaintRepo.findComplaintById(id);
        if (complaint1==null){
            throw new ApiException("id not found");
        }
        complaint1.setTitle(complaint.getTitle());
        complaint1.setDescription(complaint.getDescription());
        complaint1.setStatus(complaint.getStatus());
        complaint1.setComplaint_date(complaint.getComplaint_date());
        complaintRepo.save(complaint1);

    }
    public void deleteComplaint(Integer id){
        Complaint complaint = complaintRepo.findComplaintById(id);
        if (complaint==null){
            throw new ApiException("id not found");
        }
        complaintRepo.delete(complaint);

    }
}
