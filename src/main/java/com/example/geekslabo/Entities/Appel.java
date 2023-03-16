package com.example.geekslabo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Appel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    int quantity;
    String description;

    @Temporal(TemporalType.TIMESTAMP)
    Date datedebut;

    @Temporal(TemporalType.TIMESTAMP)
    Date datefin;
    @JsonIgnore
    @OneToMany(mappedBy = "appels")
    Set<Material> materials = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "appelDemand",cascade = CascadeType.ALL)
    Set<Demand> demands = new HashSet<>();



    @JsonIgnore
    @ManyToOne
    AppUser user;

}
