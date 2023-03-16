package com.example.geekslabo.Repositories.ActivityRepo;

import com.example.geekslabo.Entities.ArticleLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<ArticleLike,Integer> {
}
