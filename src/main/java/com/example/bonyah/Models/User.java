package com.example.bonyah.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Currency;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "username must be not null")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String username;


    @Email(message = "email must be not null")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String email;

    @NotEmpty(message = "password must be not null")
    @Column(columnDefinition = "varchar(200) not null")
    private String password;

    @NotEmpty(message = "role must be not null")
    @Pattern(regexp = "(ADMIN|CUSTOMER|PROVIDER)")
    @Column(columnDefinition = "varchar(10) not null check(role = 'ADMIN' or role = 'CUSTOMER' or role = 'PROVIDER' )")
    private String role;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Provider provider;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;

}
