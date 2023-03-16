package com.example.geekslabo.IServices.ICoursesService;


import com.example.geekslabo.Entities.Lesson;

import java.util.List;

public interface ILessonService {
    List<Lesson> findAllLessons();
    Lesson findLessonById(Long id);
    Lesson createLesson(Lesson lesson);
    Lesson updateLesson(Long id, Lesson lessonDetails);
    void deleteLesson(Long id);
}
