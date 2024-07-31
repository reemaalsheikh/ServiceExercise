package com.example.serviceexercise.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {

    @NotNull(message = "Id should not be null!")
    private int id;

    @NotEmpty(message = "Title should not be Empty!")
    @Size(max=100)
    private String title;

    @NotEmpty(message = "Author should not be Empty!")
    @Size(min=5, max=20)
    private String author;

    @NotEmpty(message = "Content should not be Empty!")
    @Size(min=201)
    private String content;

    @NotEmpty(message = "Category should not be Empty!")
    @Pattern(regexp = "^(politics|sports|technology)$",message = "Three valid iputs only: politics,sports and technology ")
    private String category;

    @NotEmpty(message = "ImageUrl should not be Empty!")
    private String imageUrl;

    @AssertFalse
    private boolean isPublished;

   @NotNull(message = "Publish date should not be Empty!")
    @JsonFormat(pattern= "yyyy-MM-dd")
    private LocalDate publishdate;

}
