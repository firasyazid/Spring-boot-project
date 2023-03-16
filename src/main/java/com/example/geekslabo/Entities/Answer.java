package com.example.geekslabo.Entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    private Boolean correct;

    @ManyToOne
    private Question question;



    // getters and setters
}
