package com.example.geekslabo.Repositories.CourseRepo;

import com.example.geekslabo.Entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
