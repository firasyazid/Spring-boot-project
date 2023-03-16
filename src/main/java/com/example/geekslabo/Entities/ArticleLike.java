package com.example.geekslabo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;
 @AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ArticleLike implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


     @JsonIgnore
    @ManyToOne
    private Article article;

    @ManyToOne
    private AppUser user;
}
