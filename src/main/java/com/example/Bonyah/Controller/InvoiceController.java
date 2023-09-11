package com.example.Bonyah.Controller;

import com.example.Bonyah.Api.ApiResponse;
import com.example.Bonyah.Models.Invoice;
import com.example.Bonyah.Service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;
    @GetMapping("/get-all")
    public ResponseEntity getAllInvoice(){
        return ResponseEntity.status(200).body(invoiceService.getAllInvoice());
    }

    @PostMapping("/add")
    public ResponseEntity addInvoice(@RequestBody @Valid Invoice invoice){
        invoiceService.addInvoice(invoice);
        return ResponseEntity.status(200).body(new ApiResponse("Invoice added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateInvoice(@PathVariable Integer id,@RequestBody @Valid Invoice invoice){
        invoiceService.updateInvoice(id,invoice);
        return ResponseEntity.status(200).body(new ApiResponse("Invoice updated"));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInvoice(@PathVariable Integer id){
        invoiceService.deleteInvoice(id);
        return ResponseEntity.status(200).body(new ApiResponse("Invoice deleted"));

    }

}
