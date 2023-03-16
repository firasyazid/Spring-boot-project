package com.example.geekslabo.Entities;

 import lombok.*;

import javax.persistence.*;
 import javax.validation.constraints.NotBlank;
 import javax.validation.constraints.Size;

 import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity

public class Claim implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Description is required")
    @Size(min = 5, max = 50, message = "Description must be between 5 and 50 characters")
    private String Description;
    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 15, message = "Title must be between 5 and 50 characters")
    private String title;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    Status status;



    public enum Status {
        IN_PROGRESS, DONE;

    }
        @ManyToOne
        private AppUser user;




    }