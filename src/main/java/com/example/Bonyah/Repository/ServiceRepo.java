package com.example.Bonyah.Repository;

import com.example.Bonyah.Models.Provider;
import com.example.Bonyah.Models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepo extends JpaRepository<Service, Integer> {
    Service findServiceById(Integer id);

    List<Service> findServicesByCategory(String category);

    @Query("select p from Service p where p.price <= ?1")
    List<Service> findServicesByPrice(Integer price);


    @Query("select p from Service p where p.category = ?1 and p.price <= ?2")
    List<Service> findServicesByCategoryAndPrice(String category, Integer price);


    List<Service> findServicesByProvider(Provider provider);


}
