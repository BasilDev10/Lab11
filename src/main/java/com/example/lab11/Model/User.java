package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Error: username is empty!")
    @Size(min = 4 , message = "Error: username length must be more then 4")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String username;
    @NotEmpty(message = "Error: password is empty!")
    @Size(min = 4, message = "Error: password length must be more then 4")
    @Column(columnDefinition = "varchar(30) not null")
    private String password;
    @NotEmpty(message = "Error: password is empty!")
    @Email(message = "Error: email is not valid format")
    @Column(columnDefinition = "varchar(100) not null")
    private String email;
    @PastOrPresent(message = "Error: registrationDate only take today or past date")
    private LocalDate registrationDate;
}
