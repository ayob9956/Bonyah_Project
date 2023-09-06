package com.example.bonyah.Repository;

import com.example.bonyah.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User findUserById(Integer id);
}
