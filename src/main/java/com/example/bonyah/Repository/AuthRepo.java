package com.example.bonyah.Repository;

import com.example.bonyah.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepo extends JpaRepository<User, Integer> {
    User findUserById(Integer id);

    @Query("select u from User u where u.username=?1 or u.email=?1")
    User logInUsernameOrEmail(String username);
}
