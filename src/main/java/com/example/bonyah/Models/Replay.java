package com.example.bonyah.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Replay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "title must be not null")
    @Column(columnDefinition = "varchar(25) not null")

    private String title;

    @Column(columnDefinition = "varchar(50)")
    private String body;

    @Column(columnDefinition = "int not null check(rating >= 1 AND rating <=5)")
    private Integer rating;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private Product product;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private Service service;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private Customer customer;

}
