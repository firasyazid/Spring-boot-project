package com.example.geekslabo.Entities;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Enrollement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   /* @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; */

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
