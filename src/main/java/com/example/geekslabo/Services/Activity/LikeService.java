package com.example.geekslabo.Services.Activity;

import com.example.geekslabo.Entities.AppUser;
import com.example.geekslabo.Entities.Article;
import com.example.geekslabo.Entities.ArticleLike;
import com.example.geekslabo.Repositories.ActivityRepo.ArticleRepository;
import com.example.geekslabo.Repositories.ActivityRepo.LikeRepository;
import com.example.geekslabo.Repositories.UserRepo.AppUserCRUDRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class LikeService {

    LikeRepository rep;
    AppUserCRUDRepository repuser;

    ArticleRepository articlerepo;

    public ArticleLike likeArticle(Integer Idarticle , Integer userId) {

        AppUser user = repuser.findById (userId).orElse (null);
        if (user == null) {
            throw new ResponseStatusException (HttpStatus.NOT_FOUND , "User not found with id " + userId);
        }
        Article article = articlerepo.findById (Idarticle).orElse (null);
        if (user == null) {
            throw new ResponseStatusException (HttpStatus.NOT_FOUND , "article not found with id " + userId);
        }

        List<ArticleLike> articleLikes = article.getArticleLike();
        for (ArticleLike like : articleLikes) {
            if (like.getUser().equals(user)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "User has already liked this article");
            }
        }

        ArticleLike articleLike = new ArticleLike ();
        articleLike.setArticle (article);
        articleLike.setUser (user);
        rep.save (articleLike);
        return articleLike;
    }


    public Map<Integer, Integer> countArticleLikes() {
        List<Article> articles = articlerepo.findAll();
        Map<Integer, Integer> articleLikeCounts = new HashMap<> ();
        for (Article article : articles) {
            List<ArticleLike> articleLikes = article.getArticleLike();
            int likeCount = articleLikes.size();
            articleLikeCounts.put(article.getId(), likeCount);
        }
        return articleLikeCounts;
    }

    public Map<String, Object> getArticleWithMostLikes() {
        List<Article> articles = articlerepo.findAll();
        Article articleWithMostLikes = null;
        int maxLikes = 0;

        for (Article article : articles) {
            int numLikes = article.getArticleLike().size();
            if (numLikes > maxLikes) {
                maxLikes = numLikes;
                articleWithMostLikes = article;
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("article", articleWithMostLikes);
        result.put("numLikes", maxLikes);
        return result;
    }


}
