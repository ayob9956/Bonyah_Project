package com.example.bonyah.Repository;

import com.example.bonyah.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {
    Order findOrderById(Integer id);
}
