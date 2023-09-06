package com.example.bonyah.Repository;

import com.example.bonyah.Models.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepo extends JpaRepository<Complaint,Integer> {
    Complaint findComplaintById(Integer id);
}
