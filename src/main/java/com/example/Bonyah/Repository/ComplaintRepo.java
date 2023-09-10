package com.example.Bonyah.Repository;

import com.example.Bonyah.Models.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepo extends JpaRepository<Complaint, Integer> {
    Complaint findComplaintById(Integer id);

    @Query("select c from Complaint c where c.status = 'waiting'")
    List<Complaint> getAllWaitingComplaints();
}
