package com.example.bonyah.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    private Integer id;


    @Column(columnDefinition = "DOUBLE")
    private Double total;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Customer customer;
}
