package com.example.geekslabo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class Material implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    String ref;
    String nommateriel;
    @Enumerated(EnumType.STRING)
    TypeMateriel typemateriel;
    int stock;
    float prixunite;



    @JsonIgnore
    @ManyToOne
    AppUser users;

    @ManyToOne
    Appel appels;
}
