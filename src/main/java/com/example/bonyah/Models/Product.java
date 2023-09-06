package com.example.bonyah.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name must be not null")
    @Column(columnDefinition = "varchar(25) not null")
    private String name;

    @NotNull(message = "price must be not null")
    @Positive(message = "price must be positive number")
    @Column(columnDefinition = "integer not null default 0")
    private Integer price;

    @NotEmpty(message = "category must be not null")
    @Column(columnDefinition = "varchar(25) not null")
    private String category;

    @NotEmpty(message = "description must be not null")
    @Column(columnDefinition = "varchar(150) not null")
    private String description;

    @NotNull(message = "stock must be not null")
    @Column(columnDefinition = "integer not null")
    private Integer stock;


}
