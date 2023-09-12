package com.example.Bonyah.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "description must be not null")
    @Column(columnDefinition = "varchar(150) not null")
    private String customer_description;


    @NotNull(message = "price must be not null")
    @Positive(message = "price must be positive number")
    @Column(columnDefinition = "integer not null")
    private Integer customer_price;


    @Column(columnDefinition = "integer")
    private Integer provider_price;

    @Column(columnDefinition = "varchar(150)")
    private String provider_description;


    @NotEmpty(message = "location must be not null")
    @Column(columnDefinition = "varchar(30) not null")
    private String location;


    @Column(columnDefinition = "varchar(15) not null default 'waiting' check(status = 'waiting' or status = 'confirm' or status = 'reject')")
    private String status;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Service service;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Customer customer;

    @OneToOne(cascade = CascadeType.DETACH, mappedBy = "request")
    private Invoice invoice;
}
