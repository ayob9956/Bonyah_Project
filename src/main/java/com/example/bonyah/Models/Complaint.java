package com.example.bonyah.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "title must be not null")
    @Column(columnDefinition = "varchar(25) not null")
    private String title;

    @Column(columnDefinition = "varchar(50)")
    private String description;


    @Column(columnDefinition = "varchar(15) not null default 'waiting' check(status = 'waiting' or status = 'opened' or status = 'finished')")
    private String status;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime complaint_date;


    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private Customer customer;

}
