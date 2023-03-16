package com.example.geekslabo.Entities;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity

public class Demand implements Serializable {
    // le chefService fait une demande pour les equipement

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    float prixunite;
    Date datedemand;

    @ManyToOne
    Appel appelDemand;


    @ManyToOne
    AppUser user;

    @OneToOne
    private
    Appel appell;
}
