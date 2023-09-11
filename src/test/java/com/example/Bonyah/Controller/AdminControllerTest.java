package com.example.Bonyah.Controller;

import com.example.Bonyah.Api.ApiResponse;
import com.example.Bonyah.Models.User;
import com.example.Bonyah.Service.AdminService;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AdminController.class,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class AdminControllerTest {
    @MockBean
    AdminService adminService;
    @Autowired
    MockMvc mockMvc;
    User user1;
    User user2;
    User user3;
    User user;
    ApiResponse apiResponse;
    List<User>users,userList;


    @BeforeEach
    void setUp() {
        user1 = new User(1,"aziz","aziz@gmail.com","123456789","ADMIN",null,null);
        user2 = new User(2,"nasser","nasser@gmail.com","123456789","ADMIN",null,null);
        user3 = new User(3,"ayoub","ayoub@gmail.com","123456789","CUSTOMER",null,null);
        users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users=Arrays.asList(user1);
        userList=Arrays.asList(user2);


    }

    @Test
    void getAllUsers() throws Exception {
    Mockito.when(adminService.getAllUsers()).thenReturn(users);
    mockMvc.perform(get("/api/v1/admin/get-all-users"))
            .andExpect(status().isOk());

    }
    @Test
    void getOneUser() throws Exception{
        Mockito.when(adminService.getOneUser(user1.getId())).thenReturn(user);
        mockMvc.perform(get("/api/v1/admin//get-user/{id}",user1.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(delete("/api/v1/admin/delete-user/{id}",user1.getId()))
                .andExpect(status().isOk());

    }
    @Test
    void getUsersByRole() throws Exception{
        Mockito.when(adminService.getUsersByRole(user1.getRole())).thenReturn(users);
        mockMvc.perform(get("/api/v1/admin/get-users-role/{role}",user1.getRole()))
                .andExpect(status().isOk());
    }




}