package com.example.bonyah.DTO;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RejectRequestDTO {


    @NotEmpty(message = "provider description must not be null")
    @Size(max = 150, message = "sorry! description must be less than 150 letters")
    private String provider_description;

    @Positive(message = "price must be positive number")
    private Integer provider_price;
    ;
}
