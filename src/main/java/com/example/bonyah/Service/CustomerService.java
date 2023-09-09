package com.example.bonyah.Service;

import com.example.bonyah.Api.ApiException;
import com.example.bonyah.DTO.CustomerDTO;
import com.example.bonyah.Models.*;
import com.example.bonyah.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final AuthRepo authRepo;
    private final ProductRepo productRepo;
    private final ProviderRepo providerRepo;
    private final ServiceRepo serviceRepo;
    private final OrderRepo orderRepo;
    private final RequestRepo requestRepo;

    public User getMyCustomer(Integer user_id){
        User user1 = authRepo.findUserById(user_id);

        if(user1!=null){

            return user1;
        }
        else {
            throw new ApiException("customer is not founded");
        }


    }
    public void updateCustomer(Integer user_id, CustomerDTO customerDTO){
        User user1 =authRepo.findUserById(user_id);

        if (user1!=null &&user1.getRole().equalsIgnoreCase("customer")){
            String hash = new BCryptPasswordEncoder().encode(customerDTO.getPassword());

            user1.setEmail(customerDTO.getEmail());
            user1.setUsername(customerDTO.getUsername());
            user1.setPassword(hash);
            user1.getCustomer().setName(customerDTO.getName());
            user1.getCustomer().setPhone(customerDTO.getPhone());
            authRepo.save(user1);
        }
        else{
        throw new ApiException("user not found");
        }


    }
    public void deleteCustomer(Integer user_id){
        User user1 = authRepo.findUserById(user_id);
        if (user1!=null &&user1.getRole().equalsIgnoreCase("customer")){
            authRepo.delete(user1);
        }

        throw new ApiException("user not found");
    }
    public Provider findProviderByName(String providerName){
        Provider provider = providerRepo.findProviderByName(providerName);
        if (provider==null){
            throw new ApiException("wrong provider name");
        }
        return provider;
    }
    public List<Product> findProductByCategory(String category){
        List<Product> products =productRepo.findProductsByCategory(category);
        if (products==null){
            throw new ApiException("there are no products have category " + category);
        }
        return products;
    }
    public List<Product> findProductByPrice(Integer price){
        List<Product> products =productRepo.findProductsByPrice(price);
        if (products==null){
            throw new ApiException("there are no products have price " + price);
        }
        return products;
    }
    public List<Product> findProductByCategoryAndPrice(String category,Integer price){
        List<Product> products = productRepo.findProductsByCategoryAndPrice(category,price);
        if (products==null) {
            throw new ApiException("there are no products have category " + category + " and price " + price);
        }
        return products;
    }
    public List<com.example.bonyah.Models.Service>findServicesByCategory(String category){
        List<com.example.bonyah.Models.Service> services = serviceRepo.findServicesByCategory(category);
        if (services==null){
            throw new ApiException("there are no services have category "+category);
        }
        return services;
    }
    public List<com.example.bonyah.Models.Service>findServicesByPrice(Integer price){
        List<com.example.bonyah.Models.Service> services = serviceRepo.findServicesByPrice(price);
        if (services==null){
            throw new ApiException("there are no services have price "+price);
        }
        return services;
    }
    public List<com.example.bonyah.Models.Service>findServicesByCategoryAndPrice(String category,Integer price){
        List<com.example.bonyah.Models.Service> services = serviceRepo.findServicesByCategoryAndPrice(category,price);
        if (services==null){
            throw new ApiException("there are no services have category " + category +" and price " + price);
        }
        return services;
    }
    public List<Orders> getMyOrders(Integer customer_id){
        Customer customer = customerRepo.findCustomerById(customer_id);
        return orderRepo.findOrdersByCustomer(customer);
    }
    public void sendOrder(Integer customer_id,Integer product_id, Orders orders){
        Product product = productRepo.findProductById(product_id);

        if (product == null){
            throw new ApiException("product not found");
        }

        Customer customer=customerRepo.findCustomerById(customer_id);
        orders.setCustomer(customer);
        orders.setProduct(product);

        orders.setTotal(orders.getProduct().getPrice() * orders.getQuantity());
        orders.setStatus("waiting");
        orderRepo.save(orders);

    }
    public void UpdateOrder(Integer customer_id,Integer order_id,Orders orders){
        Orders orders1 =orderRepo.findOrdersById(order_id);
        if (orders1!=null&&orders1.getCustomer().getId().equals(customer_id)){
            orders1.setQuantity(orders.getQuantity());
            orders1.setStatus("waiting");
            orders1.setTotal((orders.getProduct().getPrice() * orders.getQuantity()));
            orders1.setLocation(orders.getLocation());
            orders1.setOrder_date(orders.getOrder_date());
            orderRepo.save(orders1);

        }
        else {
            throw new ApiException("order not found");
        }
    }
    public void deleteOrders(Integer customer_id,Integer order_id){
        Orders orders1 =orderRepo.findOrdersById(order_id);

        if (orders1 == null){
            throw new ApiException("order not found");
        }

        if (orders1.getCustomer().getId().equals(customer_id)){
            orderRepo.delete(orders1);
        }else {
            throw new ApiException("you are not allowed to delete this order");
        }

    }
    public List<Request> getRequest(Integer customer_id){
        Customer customer = customerRepo.findCustomerById(customer_id);
        return requestRepo.findRequestByCustomer (customer);
    }
    public void sendRequest(Integer customer_id,Integer service_id, Request request){
        com.example.bonyah.Models.Service service = serviceRepo.findServiceById(service_id);

        if (service == null){
            throw new ApiException("service not found");
        }

        Customer customer=customerRepo.findCustomerById(customer_id);
        request.setCustomer(customer);
        request.setService(service);
        request.setStatus("waiting");
        requestRepo.save(request);

    }
    public void UpdateRequest(Integer customer_id,Integer request_id,Request request){
        Request request1 =requestRepo.findRequestById(request_id);
        if (request1!=null&&request1.getCustomer().getId().equals(customer_id)){
                request1.setCustomer_price(request.getCustomer_price());
                request1.setDescription(request.getDescription());
                request1.setLocation(request.getLocation());


        }
        else {
            throw new ApiException("request not found");
        }
    }

    public void deleteRequest(Integer customer_id,Integer request_id){
        Request request =requestRepo.findRequestById(request_id);

        if (request == null){
            throw new ApiException("Request not found");
        }

        if (request.getCustomer().getId().equals(customer_id)){
            requestRepo.delete(request);
        }else {
            throw new ApiException("you are not allowed to delete this request");
        }

    }








}
