package com.example.Bonyah.Repository;

import com.example.Bonyah.Models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepo extends JpaRepository<Provider,Integer> {
    Provider findProviderById(Integer id);
    Provider findProviderByName(String name);

}
