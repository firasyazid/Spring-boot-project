package com.example.geekslabo.Entities;

import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Builder
@Entity
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id ;
    @Column(name = "UserName")
    String userName ;
    String password ;
    UserRole role ;
    String nom ;
    String prenom ;
    int phone ;
    String email ;

    int salaire ; // pour le formateur et le chef service et les biologiste
    int work_day_number ; // pour les biologiste
    int holiday_number ; // pour les biologiste
    String Social_number ; // pour les patient
    String Adresse ; // pour les fournisseur
    Specialite Specialite ; // pour le formateur


}
