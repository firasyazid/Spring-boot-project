package com.example.geekslabo.Repositories.MaterialRepo;


import com.example.geekslabo.Entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material,Integer> {
}
