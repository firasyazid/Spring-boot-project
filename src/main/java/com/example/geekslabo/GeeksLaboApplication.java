package com.example.geekslabo;

import com.example.geekslabo.Entities.Article;
import com.example.geekslabo.IServices.IServiceActivities.IArticleService;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
 public class GeeksLaboApplication {

    static IArticleService serv;

    public static void main(String[] args) {
        SpringApplication.run (GeeksLaboApplication.class , args);


    }
}
