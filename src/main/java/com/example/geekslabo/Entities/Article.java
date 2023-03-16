package com.example.geekslabo.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity

public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Description is required")
    @Size(min = 5, max = 50, message = "Description must be between 5 and 50 characters")
    private String Description;
    @NotBlank(message = "Contenu is required")
    @Size(min = 10, message = "Contenu must be at least 10 characters")
    private String Contenu;
    @NotBlank(message = "Image is required")
    private String image;
    private String summary; // résumé de l'article
    @OneToMany(mappedBy = "article")
    private List<ArticleLike> articleLike = new ArrayList<>();
 }
