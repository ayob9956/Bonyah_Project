package com.example.bonyah.Service;


import com.example.bonyah.DTO.CustomerDTO;
import com.example.bonyah.DTO.ProviderDTO;
import com.example.bonyah.Models.Customer;
import com.example.bonyah.Models.Provider;
import com.example.bonyah.Models.User;
import com.example.bonyah.Repository.AuthRepo;
import com.example.bonyah.Repository.CustomerRepo;
import com.example.bonyah.Repository.ProviderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServices {

    private final CustomerRepo customerRepo;
    private final ProviderRepo providerRepo;

    public void customerRegister(CustomerDTO customerDTO) {

        String hash = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        customerDTO.setPassword(hash);

        User user = new User(null, customerDTO.getUsername(), customerDTO.getEmail(), customerDTO.getPassword(), "CUSTOMER", null, null);


        Customer customer = new Customer(null, customerDTO.getName(), 0, customerDTO.getPhone(), user, null, null, null, null);

        customerRepo.save(customer);

    }


    public void providerRegister(ProviderDTO providerDTO) {

        String hash = new BCryptPasswordEncoder().encode(providerDTO.getPassword());
        providerDTO.setPassword(hash);

        User user = new User(null, providerDTO.getUsername(), providerDTO.getEmail(), providerDTO.getPassword(), "PROVIDER", null, null);


        Provider provider = new Provider(null, providerDTO.getName(), providerDTO.getCommercialRecord(), providerDTO.getPhone(), 0, providerDTO.getLocation(), user, null, null);

        providerRepo.save(provider);

    }


}
