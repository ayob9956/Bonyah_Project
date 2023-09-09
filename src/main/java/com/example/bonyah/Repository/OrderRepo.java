package com.example.bonyah.Repository;

import com.example.bonyah.Models.Customer;
import com.example.bonyah.Models.Orders;
import com.example.bonyah.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {
    Orders findOrdersById(Integer id);

    List<Orders> findOrdersByCustomer(Customer customer);


}
