package com.example.Bonyah.Service;

import com.example.Bonyah.Api.ApiException;
import com.example.Bonyah.Models.Request;
import com.example.Bonyah.Repository.RequestRepo;
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
        request1.setCustomer_description(request.getCustomer_description());
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
