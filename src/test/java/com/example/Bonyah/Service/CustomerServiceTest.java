package com.example.Bonyah.Service;

import com.example.Bonyah.Models.Product;
import com.example.Bonyah.Models.Service;
import com.example.Bonyah.Repository.CustomerRepo;
import com.example.Bonyah.Repository.ProductRepo;
import com.example.Bonyah.Repository.ServiceRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @InjectMocks
    CustomerService customerService;
    @Mock
    CustomerRepo customerRepo;
    @Mock
    ProductRepo productRepo;
    @Mock
    ServiceRepo serviceRepo;


    Product product1;
    Product product2;
    Product product3;
    Service service1;
    Service service2;

    List<Product> products;
    List<Service> services;
    int price;
    String category;

    @BeforeEach
    void setUp() {
        product1 =new Product(1,"Sabic",3000,"iron","Reinforcing steel",300,null,null,null);
        product2 =new Product(2,"Rajhi Steel",3500,"iron","Reinforcing steel",400,null,null,null);
        product3 =new Product(2,"yamama",15,"Cement","Cement bags",60,null,null,null);
        products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        service1=new Service(1,"amjad","Electrical wiring","Electrical ",300,null,null,null);
        service2=new Service(2,"alhadab","creating structures such as buildings","Building Construction ",300000,null,null,null);
        services = new ArrayList<>();
        services.add(service1);
        services.add(service2);




    }


    @Test
    void findProductByPrice() {
        when(productRepo.findProductsByPrice(3000)).thenReturn(products);
        List<Product> productList=customerService.findProductByPrice(3000);
       Assertions.assertEquals(productList,products);
        verify(productRepo,times(1)).findProductsByPrice(3000);
    }

    @Test
    void findProductByCategory() {
        when(productRepo.findProductsByCategory(category)).thenReturn(products);
        List<Product> productList=customerService.findProductByCategory(category);
        Assertions.assertEquals(productList,products);
        verify(productRepo,times(1)).findProductsByCategory(category);
    }
    @Test
    void findProductByCategoryAndPrice(){
        when(productRepo.findProductsByCategoryAndPrice(category,price)).thenReturn(products);
        List<Product> productList=customerService.findProductByCategoryAndPrice(category,price);
        Assertions.assertEquals(productList,products);
        verify(productRepo,times(1)).findProductsByCategoryAndPrice(category,price);

    }
    @Test
    void findServicesByCategory() {
        when(serviceRepo.findServicesByCategory(category)).thenReturn(services);
        List<Service> serviceList=customerService.findServicesByCategory(category);
        Assertions.assertEquals(serviceList,services);
        verify(serviceRepo,times(1)).findServicesByCategory(category);
    }
    @Test
    void findServicesByPrice() {
        when(serviceRepo.findServicesByPrice(price)).thenReturn(services);
        List<Service> serviceList=customerService.findServicesByPrice(price);
        Assertions.assertEquals(serviceList,services);
        verify(serviceRepo,times(1)).findServicesByPrice(price);
    }



}