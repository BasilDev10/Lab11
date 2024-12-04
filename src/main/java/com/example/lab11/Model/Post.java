package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Error: title is empty!")
    @Size(min = 4 , message = "Error: title length must be more then 4")
    @Column(columnDefinition = "varchar(30) not null")
    private String title;
    @NotEmpty(message = "Error: content is empty!")
    @Size(min = 4 , message = "Error: content length must be more then 4")
    @Column(columnDefinition = "varchar(300) not null")
    private String content;
    @PastOrPresent(message = "Error: publishDate only take today or past date")
    private LocalDate publishDate;
    @NotNull(message = "Error: categoryId is null!")
    @Column(columnDefinition = "int not null")
    private Integer categoryId;
    @NotNull(message = "Error: userId is null!")
    @Column(columnDefinition = "int not null")
    private Integer userId;
}
