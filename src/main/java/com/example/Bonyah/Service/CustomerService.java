package com.example.Bonyah.Service;

import com.example.Bonyah.Api.ApiException;
import com.example.Bonyah.DTO.CustomerDTO;
import com.example.Bonyah.Models.*;
import com.example.Bonyah.Repository.*;
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
    private final InvoiceRepo invoiceRepo;
    private final ComplaintRepo complaintRepo;

    public User getMyCustomer(Integer user_id) {
        User user1 = authRepo.findUserById(user_id);

        if (user1 != null) {

            return user1;
        } else {
            throw new ApiException("customer is not founded");
        }


    }

    public void updateCustomer(Integer user_id, CustomerDTO customerDTO) {
        User user1 = authRepo.findUserById(user_id);

        if (user1 != null && user1.getRole().equalsIgnoreCase("customer")) {
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

    public void deleteCustomer(Integer user_id) {
        User user1 = authRepo.findUserById(user_id);
        if (user1 != null && user1.getRole().equalsIgnoreCase("customer")) {
            authRepo.delete(user1);
        }

        throw new ApiException("user not found");
    }

    public Provider findProviderByName(String providerName) {
        Provider provider = providerRepo.findProviderByName(providerName);
        if (provider == null) {
            throw new ApiException("wrong provider name");
        }
        return provider;
    }

    public List<Product> findProductByCategory(String category) {
        List<Product> products = productRepo.findProductsByCategory(category);
        if (products == null) {
            throw new ApiException("there are no products have category " + category);
        }
        return products;
    }

    public List<Product> findProductByPrice(Integer price) {
        List<Product> products = productRepo.findProductsByPrice(price);
        if (products == null) {
            throw new ApiException("there are no products have price " + price);
        }
        return products;
    }

    public List<Product> findProductByCategoryAndPrice(String category, Integer price) {
        List<Product> products = productRepo.findProductsByCategoryAndPrice(category, price);
        if (products == null) {
            throw new ApiException("there are no products have category " + category + " and price " + price);
        }
        return products;
    }

    public List<com.example.Bonyah.Models.Service> findServicesByCategory(String category) {
        List<com.example.Bonyah.Models.Service> services = serviceRepo.findServicesByCategory(category);
        if (services == null) {
            throw new ApiException("there are no services have category " + category);
        }
        return services;
    }

    public List<com.example.Bonyah.Models.Service> findServicesByPrice(Integer price) {
        List<com.example.Bonyah.Models.Service> services = serviceRepo.findServicesByPrice(price);
        if (services == null) {
            throw new ApiException("there are no services have price " + price);
        }
        return services;
    }

    public List<com.example.Bonyah.Models.Service> findServicesByCategoryAndPrice(String category, Integer price) {
        List<com.example.Bonyah.Models.Service> services = serviceRepo.findServicesByCategoryAndPrice(category, price);
        if (services == null) {
            throw new ApiException("there are no services have category " + category + " and price " + price);
        }
        return services;
    }


    public List<Orders> getMyOrders(Integer customer_id) {
        Customer customer = customerRepo.findCustomerById(customer_id);
        return orderRepo.findOrdersByCustomer(customer);
    }

    public void sendOrder(Integer customer_id, Integer product_id, Orders orders) {
        Product product = productRepo.findProductById(product_id);

        if (product == null) {
            throw new ApiException("product not found");
        }


        Customer customer = customerRepo.findCustomerById(customer_id);
        orders.setCustomer(customer);

        orders.setProduct(product);

        orders.setTotal(orders.getProduct().getPrice() * orders.getQuantity());
        orders.setStatus("waiting");
        orderRepo.save(orders);

    }


    public void UpdateOrder(Integer customer_id, Integer order_id, Orders orders) {
        Orders orders1 = orderRepo.findOrdersById(order_id);
        if (orders1 != null && orders1.getCustomer().getId().equals(customer_id)) {
            orders1.setQuantity(orders.getQuantity());
            orders1.setStatus("waiting");
            orders1.setTotal((orders.getProduct().getPrice() * orders.getQuantity()));
            orders1.setLocation(orders.getLocation());
            orders1.setOrder_date(orders.getOrder_date());
            orderRepo.save(orders1);

        } else {
            throw new ApiException("order not found");
        }
    }


    public void deleteOrders(Integer customer_id, Integer order_id) {
        Orders orders1 = orderRepo.findOrdersById(order_id);

        if (orders1 == null) {
            throw new ApiException("order not found");
        }


        if (orders1.getCustomer().getId().equals(customer_id)) {

            orderRepo.delete(orders1);
        } else {
            throw new ApiException("you are not allowed to delete this order");
        }
    }

    public List<Request> getRequest(Integer customer_id) {
        Customer customer = customerRepo.findCustomerById(customer_id);
        return requestRepo.findRequestByCustomer(customer);
    }



    public void sendRequest(Integer customer_id,Integer service_id, Request request){
        com.example.Bonyah.Models.Service service = serviceRepo.findServiceById(service_id);

        if (service == null) {
            throw new ApiException("service not found");
        }

        User user = authRepo.findUserById(customer_id);
        request.setCustomer(user.getCustomer());
        Customer customer = customerRepo.findCustomerById(customer_id);
        request.setCustomer(customer);
        request.setService(service);
        request.setStatus("waiting");
        requestRepo.save(request);

    }


    public void UpdateRequest(Integer customer_id, Integer request_id, Request request) {
        Request request1 = requestRepo.findRequestById(request_id);
        if (request1 != null && request1.getCustomer().getId().equals(customer_id)) {
            request1.setCustomer_price(request.getCustomer_price());
            request1.setCustomer_description(request.getCustomer_description());
            request1.setLocation(request.getLocation());


        } else {
            throw new ApiException("request not found");
        }
    }


    public void deleteRequest(Integer customer_id, Integer request_id) {
        Request request = requestRepo.findRequestById(request_id);

        if (request == null) {
            throw new ApiException("Request not found");
        }


        if (request.getCustomer().getId().equals(customer_id)) {
            requestRepo.delete(request);
        } else {
            throw new ApiException("you are not allowed to delete this request");
        }

    }


    public void payOrderInvoice(Integer userId, Integer orderID) {
        Customer customer = customerRepo.findCustomerById(userId);
        Orders orders = orderRepo.findOrdersById(orderID);

        if (orders == null) {
            throw new ApiException("order not found");
        } else if (!orders.getCustomer().getId().equals(userId)) {
            throw new ApiException("this Invoice isn't belong to you");
        } else if (customer.getBalance() < orders.getTotal()) {
            throw new ApiException("sorry! your balance less than total of order ");
        } else if (!orders.getStatus().equals("confirm")) {
            throw new ApiException("sorry! your order isn't confirmed yet ");
        }
        Product product = productRepo.findProductById(orders.getProduct().getId());
        Invoice invoice = invoiceRepo.findInvoiceById(orderID);
        if (invoice.getStatus().equals("paid")) {
            throw new ApiException("sorry! this invoice is already paid");
        } else if (invoice.getStatus().equals("canceled")) {
            throw new ApiException("sorry! this invoice is already canceled");
        }

        Provider provider = product.getProvider();

        customer.setBalance(customer.getBalance() - orders.getTotal());
        provider.setBalance(provider.getBalance() + orders.getTotal());
        product.setStock(product.getStock() - orders.getQuantity());
        invoice.setStatus("paid");
        invoice.setTotal(orders.getTotal());

        invoiceRepo.save(invoice);
        customerRepo.save(customer);
        providerRepo.save(provider);
        productRepo.save(product);
    }

    public void payRequestInvoice(Integer userId, Integer requestId) {
        Customer customer = customerRepo.findCustomerById(userId);
        Request request = requestRepo.findRequestById(requestId);

        if (request == null) {
            throw new ApiException("request not found");
        } else if (!request.getCustomer().getId().equals(userId)) {
            throw new ApiException("this Invoice isn't belong to you");
        } else if (customer.getBalance() < request.getProvider_price()) {
            throw new ApiException("sorry! your balance less than request price ");
        } else if (!request.getStatus().equals("confirm")) {
            throw new ApiException("sorry! your request isn't confirmed yet ");
        }
        com.example.Bonyah.Models.Service service = serviceRepo.findServiceById(request.getService().getId());
        Invoice invoice = invoiceRepo.findInvoiceById(requestId);

        if (invoice.getStatus().equals("paid")) {
            throw new ApiException("sorry! this invoice is already paid");
        } else if (invoice.getStatus().equals("canceled")) {
            throw new ApiException("sorry! this invoice is already canceled");
        }

        Provider provider = service.getProvider();

        customer.setBalance(customer.getBalance() - request.getProvider_price());
        provider.setBalance(provider.getBalance() + request.getProvider_price());
        invoice.setStatus("paid");
        invoice.setTotal(request.getProvider_price());

        invoiceRepo.save(invoice);
        customerRepo.save(customer);
        providerRepo.save(provider);
        serviceRepo.save(service);
    }

    public List<Invoice> getMyInvoices(Integer customerId) {
        Customer customer = customerRepo.findCustomerById(customerId);

        if (customer == null) {
            throw new ApiException("sorry! customer not found");
        }

        return invoiceRepo.findAllByCustomer(customer);
    }

    public void cancelInvoice(Integer customerId, Integer invoiceId) {
        Customer customer = customerRepo.findCustomerById(customerId);
        Invoice invoice = invoiceRepo.findInvoiceById(invoiceId);

        if (invoice == null) {
            throw new ApiException("sorry! invoice not found");
        } else if (customer == null) {
            throw new ApiException("sorry! customer not found");
        }

        if (!invoice.getCustomer().equals(customer)) {
            throw new ApiException("this Invoice isn't belong to you");
        }

        invoice.setStatus("canceled");
        invoiceRepo.save(invoice);

    }


    public void sendComplaint(Integer userId, Complaint complaint) {
        Customer customer = customerRepo.findCustomerById(userId);
        complaint.setCustomer(customer);
        complaint.setStatus("waiting");
        complaintRepo.save(complaint);
    }


}