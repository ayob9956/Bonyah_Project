package com.example.Bonyah.Repository;

import com.example.Bonyah.Models.Customer;
import com.example.Bonyah.Models.Provider;
import com.example.Bonyah.Models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepo extends JpaRepository<Request, Integer> {
    Request findRequestById(Integer id);

    List<Request> findRequestByCustomer(Customer customer);
    
    @Query("SELECT o FROM Request o WHERE o.service.provider.id = ?1")
    List<Request> findRequestByProvider(Integer providerId);

}
