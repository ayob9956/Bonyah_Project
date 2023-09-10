package com.example.bonyah.Service;


import com.example.bonyah.Api.ApiException;
import com.example.bonyah.Models.*;

import com.example.bonyah.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepo providerRepo;
    private final AuthRepo authRepo;
    private final ProductRepo productRepo;
    private final ServiceRepo serviceRepo;
    private final OrderService orderService;
    private final OrderRepo orderRepo;
    private final RequestRepo requestRepo;


    public Provider getProviderInfrometions(User user) {
        Provider provider = providerRepo.findProviderById(user.getId());
        if (provider == null) {
            throw new ApiException("Provider is not found");
        }
        if (!provider.getId().equals(user.getId())) {
            throw new ApiException(" not matching provider");
        }
        return provider;
    }

    public void updateUser(User user, Provider provider) {
        User user1 = providerRepo.findProviderById(user.getId()).getUser();

        Provider provider1 = user1.getProvider();
        if (user1 == null) {
            throw new ApiException("Provider is not found");
        }
        if (!user1.getId().equals(user.getId())) {
            throw new ApiException(" not matching provider");
        }


        provider1.setCommercialRecord(provider.getCommercialRecord());
        provider1.setName(provider.getName());
        provider1.setPhone(provider.getPhone());
        provider1.setBalance(provider.getBalance());
        provider1.setLocation(provider.getLocation());
        providerRepo.save(provider1);


    }

    public void deleteProvider(Integer Id) {
        User user = authRepo.findUserById(Id);
        if (user == null) {
            throw new ApiException("Provider is not found");
        }

        authRepo.delete(user);
    }


    public void addProduct(User user, Product product) {
        Provider provider = user.getProvider();


        if (product == null) {
            throw new ApiException("Product is not found");
        }

        if (provider == null) {
            throw new ApiException("Provider is not found");
        }
        product.setProvider(provider);
        productRepo.save(product);
    }


    public void addService(User user, com.example.bonyah.Models.Service service) {
        Provider provider = user.getProvider();


        if (service == null) {
            throw new ApiException("Service is not found");
        }

        if (provider == null) {
            throw new ApiException("Provider is not found");
        }

        service.setProvider(provider);
        serviceRepo.save(service);

    }


    public List<Orders> getMyOrders(User user) {

        Provider provider = user.getProvider();
        List<Orders> orders = orderRepo.findOrdersByProvider(provider);

        if (provider.getProducts() == null) {
            throw new ApiException("Product is not found");
        }


        return orders;
    }



    public List<Request> getRequestByProvider(User user) {

        Provider provider = user.getProvider();
        List<Request> requests = requestRepo.findRequestByProvider(provider);

        if (provider.getServices() == null) {
            throw new ApiException("Request is not found");
        }


        return requests;
    }


    public void confirmOrders(User user, Integer id) {
        Provider provider = user.getProvider();
        Orders order = orderRepo.findOrdersById(id);
        //orderRepo.findOrdersByProvider(provider, id);
        if (order == null) {
            throw new ApiException("Orders Null");
        }
//        if (!orders.getId().equals(id)){
//            throw new ApiException("Order Id not found");
//        }
        order.setStatus("confirm");
        orderRepo.save(order);

    }



    public void confirmRequest(User user, Integer id) {
        Provider provider = user.getProvider();
        Request request =requestRepo.findRequestById(id);
//                requestRepo.findByProvider(provider,id);
        if (request == null) {
            throw new ApiException("Request Null");
        }
//        if (!orders.getId().equals(id)){
//            throw new ApiException("Order Id not found");
//        }
        request.setStatus("confirm");
        requestRepo.save(request);
    }


    public void rejectOrders(User user, Integer id) {
        Provider provider = user.getProvider();
        Orders order = orderRepo.findOrdersByProvider(provider, id);
        if (order == null) {
            throw new ApiException("Orders Null");
        }
//        if (!orders.getId().equals(id)){
//            throw new ApiException("Order Id not found");
//        }
        order.setStatus("reject");
        orderRepo.save(order);

    }





    public void rejectRequest(User user, Integer id) {
        Provider provider = user.getProvider();
        Request request = requestRepo.findRequestById(id);
//                requestRepo.findByProvider(provider, id);
        if (request == null) {
            throw new ApiException("Orders Null");
        }
//        if (!orders.getId().equals(id)){
//            throw new ApiException("Order Id not found");
//        }
        request.setStatus("reject");
        requestRepo.save(request);

    }
}
