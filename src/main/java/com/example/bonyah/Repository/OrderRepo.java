package com.example.bonyah.Repository;

import com.example.bonyah.Models.Customer;
import com.example.bonyah.Models.Orders;
import com.example.bonyah.Models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {
    Orders findOrdersById(Integer id);

    @Query("SELECT o FROM Orders o WHERE o.product.provider = ?1")
    List<Orders> findOrdersByProvider(Provider provider);


    @Query("SELECT o FROM Orders o WHERE o.product.provider = ?1 AND o.id = ?2")
    Orders findOrdersByProvider(Provider provider,Integer id);

    List<Orders> findOrdersByCustomer(Customer customer);

}
