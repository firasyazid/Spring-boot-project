package com.example.geekslabo.Repositories.ActivityRepo;


import com.example.geekslabo.Entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Integer> {
}
