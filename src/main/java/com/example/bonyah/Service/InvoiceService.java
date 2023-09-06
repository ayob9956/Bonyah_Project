package com.example.bonyah.Service;

import com.example.bonyah.Api.ApiException;
import com.example.bonyah.Models.Invoice;
import com.example.bonyah.Repository.InvoiceRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepo invoiceRepo;

    public List<Invoice>getAllInvoice(){
        return invoiceRepo.findAll();
    }
    public void addInvoice(Invoice invoice){
        invoiceRepo.save(invoice);
    }
    public void updateInvoice(Integer id,Invoice invoice){
        Invoice invoice1 = invoiceRepo.findInvoiceById(id);
        if (invoice1 == null){
            throw new ApiException("id not founded");
        }
        invoice1.setInvoice_date(invoice.getInvoice_date());
        invoice1.setTotal(invoice.getTotal());
        invoice1.setStatus(invoice.getStatus());
        invoiceRepo.save(invoice1);

    }
    public void deleteInvoice(Integer id){
        Invoice invoice = invoiceRepo.findInvoiceById(id);
        if(invoice==null){
            throw new ApiException("id not founded");

        }
        invoiceRepo.delete(invoice);
    }
}
