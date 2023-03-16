package com.example.geekslabo.IServices.ICoursesService;


import com.example.geekslabo.Entities.Quiz;

import java.util.List;

public interface IQuizService {
    List<Quiz> findAllQuizzes();
    Quiz findQuizById(Long id);
    Quiz createQuiz(Quiz quiz);
    Quiz updateQuiz(Long id, Quiz quizDetails);
    void deleteQuiz(Long id);


    Quiz addQuestionToQuiz(Long quizId, Long questionId);
}
