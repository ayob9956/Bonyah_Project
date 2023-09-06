package com.example.bonyah.Repository;

import com.example.bonyah.Models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepo extends JpaRepository<Provider,Integer> {
    Provider findProviderById(Integer id);
}
