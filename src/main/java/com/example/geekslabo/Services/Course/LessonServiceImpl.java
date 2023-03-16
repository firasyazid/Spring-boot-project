package com.example.geekslabo.Services.Course;


import com.example.geekslabo.Entities.Lesson;
import com.example.geekslabo.IServices.ICoursesService.ILessonService;
import com.example.geekslabo.Repositories.CourseRepo.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements ILessonService {
    private final LessonRepository lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public List<Lesson> findAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson findLessonById(Long id) {
        return lessonRepository.findById(id).orElse(null);
    }

    @Override
    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson updateLesson(Long id, Lesson lessonDetails) {
        Lesson existingLesson = lessonRepository.findById(id)
                .orElse(null);

        existingLesson.setName(lessonDetails.getName());
        existingLesson.setDescription(lessonDetails.getDescription());
        existingLesson.setVideoUrl(lessonDetails.getVideoUrl());

        return lessonRepository.save(existingLesson);
    }

    @Override
    public void deleteLesson(Long id) {

    }
}
