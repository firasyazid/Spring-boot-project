package com.example.geekslabo.Repositories.ActivityRepo;


import com.example.geekslabo.Entities.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim,Integer> {
 Claim findByTitle(String title);


}
