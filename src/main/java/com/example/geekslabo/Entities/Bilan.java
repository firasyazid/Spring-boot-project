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

public class Bilan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private TypeBilan typeBilan;
    private String Resultat;
}
