package com.example.bonyah.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "name must be not null")
    @Column(columnDefinition = "varchar(25) not null")
    private String name;

    @NotEmpty(message = "description must be not null")
    @Column(columnDefinition = "varchar(150) not null")
    private String description;

    @NotNull(message = "price must be not null")
    @Positive(message = "price must be positive number")
    @Column(columnDefinition = "integer not null")
    private Integer price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "service")
    private Set<Replay> replays;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "service")
    private Set<Request> requests;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private Provider provider;


}
