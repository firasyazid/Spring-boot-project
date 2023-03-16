package com.example.geekslabo.Repositories.MaterialRepo;


import com.example.geekslabo.Entities.Appel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppelRepository extends JpaRepository<Appel, Integer> {
}
