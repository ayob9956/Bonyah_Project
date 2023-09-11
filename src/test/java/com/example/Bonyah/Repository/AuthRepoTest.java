package com.example.Bonyah.Repository;

import com.example.Bonyah.Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthRepoTest {
    @Autowired
    AuthRepo authRepo;
    User user1;
    User user2;
    User user3;
    List<User> users;
    @BeforeEach
    void setUp() {
        user1 = new User(1,"aziz","aziz@gmail.com","123456789","CUSTOMER",null,null);
        user2 = new User(2,"nasser","nasser@gmail.com","123456789","CUSTOMER",null,null);
        user3 = new User(3,"ayoub","ayoub@gmail.com","123456789","PROVIDER",null,null);
        users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

    }
    @Test
    void findUserById() {
        authRepo.findUserById(user1.getId());
    }

    @Test
    void findAllByRole() {
        authRepo.findAllByRole("CUSTOMER");
    }
}