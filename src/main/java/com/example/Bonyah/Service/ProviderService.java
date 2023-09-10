package com.example.Bonyah.Service;


import com.example.Bonyah.Api.ApiException;
import com.example.Bonyah.Models.*;

import com.example.Bonyah.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


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
    public void updateProduct(Integer id,Product product,User user){

        Provider provider = user.getProvider();
        Product product1=productRepo.findProductById(id);


        if (product1.getProvider() != provider){
            throw new ApiException("Provider is not matching with product");
        }

        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setCategory(product.getCategory());
        product1.setDescription(product.getDescription());
        product1.setStock(product.getStock());
        productRepo.save(product1);

    }

    public void deleteProduct(Integer id,User user) {
        Provider provider = user.getProvider();
        Product product = productRepo.findProductById(id);
       if (product.getProvider()!= provider){
           throw new ApiException("Product not matching with provider");
       }
        productRepo.delete(product);
    }


    public void addService(User user, com.example.Bonyah.Models.Service service) {
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


    public void updateService(Integer id, com.example.Bonyah.Models.Service service,User user) {
        Provider provider = user.getProvider();
        com.example.Bonyah.Models.Service service1 = serviceRepo.findServiceById(id);
      if (service1.getProvider() != provider) {
          throw new ApiException("Service Not matching provider");
      }
        service1.setName(service.getName());
        service1.setDescription(service.getDescription());
        service1.setPrice(service.getPrice());
        serviceRepo.save(service1);
    }

    public void deleteService(Integer id,User user){
        com.example.Bonyah.Models.Service service = serviceRepo.findServiceById(id);
        Provider provider = service.getProvider();
        if (service.getProvider()!=provider){
            throw new ApiException("Service Not matching provider");
        }
        serviceRepo.delete(service);
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
