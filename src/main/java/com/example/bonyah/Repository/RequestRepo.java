package com.example.bonyah.Repository;

import com.example.bonyah.Models.Customer;
import com.example.bonyah.Models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepo extends JpaRepository<Request,Integer> {
    Request findRequestById(Integer id);
    List<Request>findRequestByCustomer(Customer customer);

}
