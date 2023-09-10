package com.example.bonyah.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(columnDefinition = "integer not null")
    private Integer total;

    @Column(columnDefinition = "varchar(15) not null default 'unpaid' check(status = 'unpaid' or status = 'paid' or status = 'canceled')")
    private String status;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime invoice_date = LocalDateTime.now();

    @OneToOne
    @MapsId
    @JsonIgnore
    private Request request;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Orders orders;


    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private Customer customer;
}
