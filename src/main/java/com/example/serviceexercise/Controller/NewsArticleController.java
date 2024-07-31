package com.example.serviceexercise.Controller;

import com.example.serviceexercise.Api.ApiResponse;
import com.example.serviceexercise.Model.NewsArticle;
import com.example.serviceexercise.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/article")
@RequiredArgsConstructor
public class NewsArticleController {

  private final NewsArticleService newsArticleService;

//1. Get all NewsArticles.
    @GetMapping("/get/all")
   public ResponseEntity getAllArticles (){

   ArrayList<NewsArticle> newsArticles = newsArticleService.getNewsArticles();
   return ResponseEntity.status(200).body(newsArticles);
   }

//2. Add a NewsArticle.
    @PostMapping("/add")
public ResponseEntity addArticle (@Valid @RequestBody NewsArticle newsArticle, Errors errors){
        if(errors.hasErrors()){
           String message = errors.getFieldError().getDefaultMessage();
           return ResponseEntity.status(400).body(message);
        }
        newsArticleService.addArticle(newsArticle);
        return ResponseEntity.status(200).body(new ApiResponse( "The Article added Successfully."));


}

//3. Update a NewsArticle.
@PutMapping("/update/{id}")
    public ResponseEntity updateArticle (@PathVariable int id,@Valid @RequestBody NewsArticle newsArticle, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = newsArticleService.updateArticle(id, newsArticle);
        if (isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse( "The Article is Updated Successfully."));
        }
        return ResponseEntity.status(400).body(new ApiResponse( "Article Not Found."));
    }

// 4. Delete a NewsArticle.
@DeleteMapping("/delete/{id}")
    public ResponseEntity deleteArticle (@PathVariable int id){
        boolean isDeleted = newsArticleService.deleteArticle(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse( "The Article is Deleted Successfully."));
        }
        return ResponseEntity.status(400).body(new ApiResponse( "Article Not Found."));
    }

//5. Publish NewsArticles.
    @PutMapping("publish/{id}")
public ResponseEntity publishArticle (@PathVariable int id){
        boolean isPublished = newsArticleService.publishArticle(id);
        if (isPublished){
            return ResponseEntity.status(200).body(new ApiResponse( "The Article is Published Successfully."));
        }
        return ResponseEntity.status(400).body(new ApiResponse( "Article Not Found."));
}

//6. Get all Published NewsArticles.
@GetMapping("/get/published")
public ResponseEntity getAllpublishedArticles (){
   return ResponseEntity.status(200).body(newsArticleService.getAllpublishedArticle());
}

//7. Get NewsArticles by Category.
 @GetMapping("/get/{category}")
 public ResponseEntity NewsArticleByCategory(@PathVariable String category){
    ArrayList <NewsArticle> na = newsArticleService.NewsArticlesbyCategory(category);
    if (na.isEmpty()){
        return ResponseEntity.status(400).body(new ApiResponse("No Article Found whith this category name: "+ category));
    }
     return ResponseEntity.status(200).body(na);


 }



}
