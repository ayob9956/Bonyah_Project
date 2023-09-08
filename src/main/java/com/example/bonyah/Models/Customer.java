package com.example.bonyah.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
public class Customer {

    @Id
    private Integer id;

    @Column(columnDefinition = "varchar(25) not null unique")
    private String name;


    @Column(columnDefinition = "integer not null default 0")
    private Integer balance;


    @Column(columnDefinition = "varchar(10) not null unique")
    private String phone;

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Request> requests;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Complaint> complaints;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Orders> orders;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")

    private Set<Replay> replays;
}

