package com.example.bonyah.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Currency;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(25) not null unique")
    private String username;


    @Column(columnDefinition = "varchar(25) not null unique")
    private String email;

    @Column(columnDefinition = "varchar(200) not null")
    private String password;


    @Column(columnDefinition = "varchar(10) not null check(role = 'ADMIN' or role = 'CUSTOMER' or role = 'PROVIDER' )")
    private String role;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Provider provider;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
