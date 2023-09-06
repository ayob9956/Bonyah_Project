package com.example.bonyah.Service;

import com.example.bonyah.Api.ApiException;
import com.example.bonyah.Models.Order;
import com.example.bonyah.Repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;

    public List<Order>getAllOrder(){
        return orderRepo.findAll();
    }
    public void addOrder(Order order){
        orderRepo.save(order);
    }
    public void updateOrder(Integer id,Order order){
        Order order1=orderRepo.findOrderById(id);
        if(order1==null){
            throw new ApiException("id not founded");
        }
        order1.setQuantity(order.getQuantity());
        order1.setStatus(order.getStatus());
        order1.setTotal(order.getTotal());
        order1.setLocation(order.getLocation());
        order1.setDateOrder(order.getDateOrder());
        orderRepo.save(order1);
    }
    public void deleteOrder(Integer id){
        Order order=orderRepo.findOrderById(id);
        if(order==null){
            throw new ApiException("id not founded");
        }
        orderRepo.delete(order);
    }
}
