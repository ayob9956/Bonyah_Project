package com.example.bonyah.Repository;

import com.example.bonyah.Models.Customer;
import com.example.bonyah.Models.Orders;
import com.example.bonyah.Models.Provider;
import com.example.bonyah.Models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepo extends JpaRepository<Request,Integer> {
    Request findRequestById(Integer id);
    List<Request>findRequestByCustomer(Customer customer);



    @Query("SELECT o FROM Orders o WHERE o.product.provider = ?1 AND o.id = ?2")
    Request findByProvider(Provider provider,Integer id);
    @Query("SELECT o FROM Orders o WHERE o.product.provider = ?1")
    List<Request> findRequestByProvider(Provider provider);
}
