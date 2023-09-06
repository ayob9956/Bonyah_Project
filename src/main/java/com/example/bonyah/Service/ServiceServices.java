package com.example.bonyah.Service;

import com.example.bonyah.Api.ApiException;
import com.example.bonyah.Repository.ServiceRepo;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceServices {
    private final ServiceRepo serviceRepo;
    public List<com.example.bonyah.Models.Service>getAllService(){
        return serviceRepo.findAll();
    }
    public void addService(com.example.bonyah.Models.Service service){
        serviceRepo.save(service);
    }
    public void updateService(Integer id, com.example.bonyah.Models.Service service){
        com.example.bonyah.Models.Service service1 = serviceRepo.findServiceById(id);
        if (service1 == null){
            throw new ApiException("id not founded");
        }
        service1.setName(service.getName());
        service1.setDescription(service.getDescription());
        service1.setPrice(service.getPrice());
        serviceRepo.save(service1);
    }
    public void deleteService(Integer id){
        com.example.bonyah.Models.Service service = serviceRepo.findServiceById(id);
        if (service==null){
            throw new ApiException("id not founded");

        }
        serviceRepo.delete(service);
    }

}
