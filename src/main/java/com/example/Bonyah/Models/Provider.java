package com.example.Bonyah.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Provider {

    @Id
    private Integer id;

    @NotEmpty(message = "name must be not null")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String name;

    @Column(columnDefinition = "varchar(30) not null unique")
    private String commercialRecord;


    @Column(columnDefinition = "varchar(10) not null unique")
    private String phone;

    @Column(columnDefinition = "integer not null default 0")
    private Integer balance;

    @Column(columnDefinition = "varchar(30) not null")
    private String location;

    @Column(columnDefinition = "varchar(15) not null default 'waiting' check(status = 'waiting' or status = 'confirmed' or status = 'reject')")
    private String status;

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
    private Set<Product> products;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
    private Set<Service> services;
}
