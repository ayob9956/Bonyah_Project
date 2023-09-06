package com.example.bonyah.Service;

import com.example.bonyah.Api.ApiException;
import com.example.bonyah.Models.Request;
import com.example.bonyah.Repository.RequestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepo requestRepo;

    public List<Request> getAllRequest() {
        return requestRepo.findAll();
    }

    public void addRequest(Request request) {
        requestRepo.save(request);
    }

    public void updateRequest(Integer id, Request request) {
        Request request1 = requestRepo.findRequestById(id);
        if (request1 == null) {
            throw new ApiException("id not founded");
        }
        request1.setDescription(request.getDescription());
        request1.setCustomer_price(request.getCustomer_price());
        request1.setProvider_price(request.getProvider_price());
        request1.setLocation(request.getLocation());
        requestRepo.save(request1);
    }

    public void deleteRequest(Integer id) {
        Request request = requestRepo.findRequestById(id);
        if (request == null) {
            throw new ApiException("id not founded");
        }
        requestRepo.delete(request);
    }
}
