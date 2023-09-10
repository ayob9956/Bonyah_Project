package com.example.bonyah.Service;


import com.example.bonyah.Api.ApiException;
import com.example.bonyah.DTO.RejectRequestDTO;
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
    private final OrderRepo orderRepo;
    private final RequestRepo requestRepo;
    private final InvoiceRepo invoiceRepo;


    public Provider getProviderInfo(User user) {
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

        if (user1 == null) {
            throw new ApiException("Provider is not found");
        }
        if (!user1.getId().equals(user.getId())) {
            throw new ApiException(" not matching provider");
        }
        Provider provider1 = user1.getProvider();

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
        Orders order = orderRepo.findOrdersById(id);
        if (order == null) {
            throw new ApiException("Orders Null");
        } else if (!order.getProduct().getProvider().getId().equals(user.getId())) {
            throw new ApiException("you're not allow to confirm this order");
        }
        Customer customer = order.getCustomer();

        order.setStatus("confirm");

        Invoice invoice = new Invoice(null, order.getTotal(), "unpaid", null, null, order, customer);

        orderRepo.save(order);
        invoiceRepo.save(invoice);

    }


    public void confirmRequest(User user, Integer id) {

        Request request = requestRepo.findRequestById(id);
        if (request == null) {
            throw new ApiException("Request Null");
        } else if (!request.getService().getProvider().getId().equals(user.getId())) {
            throw new ApiException("you're not allow to confirm this request");
        }

        Customer customer = request.getCustomer();
        request.setStatus("confirm");
        request.setProvider_price(request.getCustomer_price());
        Invoice invoice = new Invoice(null, request.getCustomer_price(), "unpaid", null, request, null, customer);

        requestRepo.save(request);
        invoiceRepo.save(invoice);

    }


    public void rejectOrders(User user, Integer id) {
        Provider provider = user.getProvider();
        Orders order = orderRepo.findOrdersByProvider(provider, id);
        if (order == null) {
            throw new ApiException("Orders Null");
        }
        order.setStatus("reject");
        orderRepo.save(order);

    }


    public void rejectRequest(User user, Integer id, RejectRequestDTO rejectRequestDTO) {
        Request request = requestRepo.findRequestById(id);
        if (request == null) {
            throw new ApiException("request doesn't exist");
        }
        if (!request.getService().getProvider().getId().equals(user.getId())) {
            throw new ApiException("you are not allow to reject this request");
        }
        request.setProvider_price(rejectRequestDTO.getProvider_price());
        request.setProvider_description(rejectRequestDTO.getProvider_description());
        request.setStatus("reject");
        requestRepo.save(request);

    }
}
