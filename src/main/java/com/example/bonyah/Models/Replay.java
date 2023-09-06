package com.example.bonyah.Models;

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
    @Column(columnDefinition = "varchar(25) not null unique")
    private String title;
    private String body;
    private Integer rating;


}
