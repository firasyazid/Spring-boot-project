package com.example.geekslabo.Repositories.MaterialRepo;

import com.example.geekslabo.Entities.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Integer> {
}
