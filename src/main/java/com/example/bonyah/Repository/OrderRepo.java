package com.example.bonyah.Repository;

import com.example.bonyah.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {
    Orders findOrdersById(Integer id);
}
