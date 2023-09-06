package com.example.bonyah.Repository;

import com.example.bonyah.Models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice,Integer> {
    Invoice findInvoiceById(Integer id);

}
