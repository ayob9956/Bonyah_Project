package com.example.Bonyah.Service;

import com.example.Bonyah.Api.ApiException;
import com.example.Bonyah.Repository.ServiceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceServices {
    private final ServiceRepo serviceRepo;
    public List<com.example.Bonyah.Models.Service>getAllService(){
        return serviceRepo.findAll();
    }
    public void addService(com.example.Bonyah.Models.Service service){
        serviceRepo.save(service);
    }


}
