package com.example.bonyah.DTO;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDTO {

    @NotEmpty(message = "username must be not null")
    private String username;


    @Email(message = "email must be not null")
    private String email;

    @NotEmpty(message = "password must be not null")
    @Column(columnDefinition = "varchar(200) not null")
    private String password;


    @NotEmpty(message = "name must be not null")
    private String name;

    @NotEmpty(message = "commercial record must be not null")
    private String commercialRecord;

    @NotEmpty(message = "name must be not null")
    @Size(max = 10, message = "phone number must be 10 digits only")
    private String phone;

    @NotEmpty(message = "location record must be not null")
    private String location;
}
