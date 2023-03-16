package com.example.geekslabo.Controllers.CourseController;


import com.example.geekslabo.Entities.*;
import com.example.geekslabo.IServices.ICoursesService.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Quizzes")
public class QuizController {

    private final IQuizService quizService;

    public QuizController(IQuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public List<Quiz> findAll() {
        return quizService.findAllQuizzes();
    }

    @GetMapping("/{id}")
    public Quiz findById(@PathVariable Long id) {
        return quizService.findQuizById(id);
    }

    @PostMapping
    public Quiz create(@RequestBody Quiz quiz) {
        return quizService.createQuiz(quiz);
    }

    @PutMapping("/{id}")
    public Quiz update(@PathVariable Long id, @RequestBody Quiz quiz) {
        return quizService.updateQuiz(id, quiz);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        quizService.deleteQuiz(id);
    }


    @PostMapping("addQuestionToQuiz/{quizId}/{questionId}")
    public Quiz addQuestionToQuiz( @PathVariable Long quizId, @PathVariable Long questionId){
        quizService.addQuestionToQuiz(quizId,questionId);
        return quizService.findQuizById(quizId);
    }

}