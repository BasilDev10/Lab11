package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Error: content is empty!")
    @Size(min = 4 , message = "Error: content length must be more then 4")
    @Column(columnDefinition = "varchar(300) not null")
    private String content;
    @PastOrPresent(message = "Error: commentDate only take today or past date")
    private LocalDate commentDate;
    @NotNull(message = "Error: postId is null!")
    @Column(columnDefinition = "int not null")
    private Integer postId;
    @NotNull(message = "Error: userId is null!")
    @Column(columnDefinition = "int not null")
    private Integer userId;

}
