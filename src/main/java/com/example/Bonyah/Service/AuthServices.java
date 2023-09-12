package com.example.Bonyah.Service;


import com.example.Bonyah.DTO.CustomerDTO;
import com.example.Bonyah.DTO.ProviderDTO;
import com.example.Bonyah.Models.Customer;
import com.example.Bonyah.Models.Provider;
import com.example.Bonyah.Models.User;
import com.example.Bonyah.Repository.CustomerRepo;
import com.example.Bonyah.Repository.ProviderRepo;
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


        Customer customer = new Customer(null, customerDTO.getName(), 0, customerDTO.getPhone(), user, null, null, null, null, null);

        customerRepo.save(customer);

    }


    public void providerRegister(ProviderDTO providerDTO) {

        String hash = new BCryptPasswordEncoder().encode(providerDTO.getPassword());
        providerDTO.setPassword(hash);

        User user = new User(null, providerDTO.getUsername(), providerDTO.getEmail(), providerDTO.getPassword(), "PROVIDER", null, null);


        Provider provider = new Provider(null, providerDTO.getName(), providerDTO.getCommercialRecord(), providerDTO.getPhone(), 0, providerDTO.getLocation(), "waiting", user, null, null);

        providerRepo.save(provider);

    }


}
