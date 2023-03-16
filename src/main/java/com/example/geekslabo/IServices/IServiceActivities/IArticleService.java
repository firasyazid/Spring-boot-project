package com.example.geekslabo.IServices.IServiceActivities;


import com.example.geekslabo.Entities.Article;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IArticleService {
    List<Article> GetallArticle();

    public Article AddArticle( Article article );

    public Article findArticlebyId( Integer id );

    public Article updateArticle( Integer id , Article article );

    void delete( Integer id );

    public Article createArticle( MultipartFile image , String description , String contenu ) throws IOException;

    public String summarizeArticle(Integer articleid);
    public void generateQRCode(Integer id);

    }
