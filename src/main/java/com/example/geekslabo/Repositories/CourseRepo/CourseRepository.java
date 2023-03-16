package com.example.geekslabo.Repositories.CourseRepo;

import com.example.geekslabo.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
     Course findByName(String name);
}

