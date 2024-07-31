package com.example.serviceexercise.Service;

import com.example.serviceexercise.Model.NewsArticle;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class NewsArticleService {

ArrayList<NewsArticle> newsArticles = new ArrayList<>();

//1. Get all NewsArticles.
public ArrayList<NewsArticle> getNewsArticles() {
    return newsArticles;
}

//2. Add a NewsArticle.
public void addArticle(NewsArticle newsArticle) {
    newsArticles.add(newsArticle);
}

//3. Update a NewsArticle.
    public boolean updateArticle(int id, NewsArticle newsArticle) {
    for (int i = 0; i < newsArticles.size(); i++) {
        if (newsArticles.get(i).getId() == id) {
            newsArticles.set(i, newsArticle);
            return true;
        }
    }
    return false;
    }


// 4. Delete a NewsArticle.
public boolean deleteArticle(int id) {
    for (int i = 0; i < newsArticles.size(); i++) {
        if (newsArticles.get(i).getId() == id) {
            newsArticles.remove(i);
            return true;
        }
    }
    return false;
}

//5. Publish NewsArticles.
public boolean publishArticle(int id) {
    for (int i = 0; i < newsArticles.size(); i++) {
        if (newsArticles.get(i).getId() == id) {
            newsArticles.get(i).setPublished(true);
            newsArticles.get(i).setPublishdate(LocalDate.now());
            return true;
        }
    }
    return false;
}


//6. Get all Published NewsArticles.
    public ArrayList<NewsArticle> getAllpublishedArticle () {
    ArrayList<NewsArticle> newPublishedArticle = new ArrayList<>();
    for (int i = 0; i < newsArticles.size(); i++) {
        if (newsArticles.get(i).isPublished()== true ){
            newPublishedArticle.add(newsArticles.get(i));
        }
    }
    return newPublishedArticle;
    }

//7. Get NewsArticles by Category.

public ArrayList<NewsArticle> NewsArticlesbyCategory(String category) {
    ArrayList<NewsArticle> newarray = new ArrayList<>();
    for (int i = 0; i < newsArticles.size(); i++) {
        if (newsArticles.get(i).getCategory().equals(category)) {
         newarray.add(newsArticles.get(i));

        }
    }
    return newarray;
}


}
