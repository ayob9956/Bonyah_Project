package com.example.Bonyah.Repository;

import com.example.Bonyah.Models.Customer;
import com.example.Bonyah.Models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {
    Invoice findInvoiceById(Integer id);

    List<Invoice> findAllByCustomer(Customer customer);

}
