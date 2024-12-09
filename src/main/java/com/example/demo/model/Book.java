package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank(message = "title should not be blank")
    @JsonProperty("title")
    private String title;

//    @NotBlank(message = "author should not be blank")
    @JsonProperty("author")
    private String author;

//    @NotBlank(message = "category should not be blank")
    @JsonProperty("category")
    private String category;

    @Column(columnDefinition = "TEXT")
    @JsonProperty("content")
    private String content;
}
