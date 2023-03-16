package com.example.geekslabo.Controllers.ActivitiesController;


import com.example.geekslabo.Entities.Article;
import com.example.geekslabo.Entities.ArticleLike;
import com.example.geekslabo.IServices.IServiceActivities.IArticleService;
import com.example.geekslabo.Services.Activity.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/Articles")
public class ArticleController {

    IArticleService serv;
    LikeService likeserv;

    @GetMapping("/list-article")
    public List<Article> GetallArticle () {
        return serv.GetallArticle();
    }

    @PostMapping("/add-article")
    public Article AddArticle (@RequestBody Article article) {
        return serv.AddArticle(article);
    }

    @DeleteMapping("/delete-article/{id}")
    public void delete (@PathVariable Integer id) {
        serv.delete(id);
    }

    @GetMapping("/find-article/{id}")
    public Article findArticlebyId (@PathVariable Integer id) {
        return serv.findArticlebyId(id);
    }

    @PutMapping("/update-article/{id}")
    public Article updateArticle (@PathVariable Integer id, @RequestBody Article article) {
        return serv.updateArticle(id, article);
    }


    @PostMapping("/articles")
    public ResponseEntity<Article> createArticle (
            @Valid @RequestParam("image") MultipartFile image,
            @Valid @RequestParam("description") String description,
            @Valid @RequestParam("contenu") String contenu
             ) throws IOException {


          Article article = serv.createArticle(image, description, contenu);
         return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    @PostMapping("/articles/{id}/{userId}/like")
    public ResponseEntity<ArticleLike> likeArticle(@PathVariable("id") Integer articleId,
                                                   @PathVariable("userId") Integer userId) {
        try {
            ArticleLike likedArticle = likeserv.likeArticle (articleId, userId);
            return ResponseEntity.ok().body(likedArticle);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatus()).body(null);
        }
    }
    @GetMapping("likes/count")
    public ResponseEntity<Map<Integer, Integer>> countArticleLikes() {
        Map<Integer, Integer> articleLikeCounts = likeserv.countArticleLikes();
        return ResponseEntity.ok(articleLikeCounts);
    }

    @GetMapping("/articles/most-liked")
    public ResponseEntity<Map<String, Object>> getArticleWithMostLikes() {
        Map<String, Object> result = new HashMap<> ();
        result = likeserv.getArticleWithMostLikes();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/summarize/{id}")
    public ResponseEntity<String> summarizeArticle(@PathVariable Integer id) {

        String summary =serv.summarizeArticle(id);
        return ResponseEntity.ok(summary);
    }
    @PostMapping("/{id}/qrcode")
    public ResponseEntity<?> generateQRCodeForArticle(@PathVariable Integer id) {
        Article article = serv.findArticlebyId (id);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            serv.generateQRCode(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate QR code");
        }
    }


}

