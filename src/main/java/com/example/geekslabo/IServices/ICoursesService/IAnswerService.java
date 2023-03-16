package com.example.geekslabo.IServices.ICoursesService;



import com.example.geekslabo.Entities.Answer;
import com.example.geekslabo.Entities.Question;

import java.util.List;

public interface IAnswerService {
    List<Answer> findAllAnswers();
    Answer findAnswerById(Long id);
    Answer createAnswer(Answer answer);
    Answer updateAnswer(Long id, Answer answerDetails);
    void deleteAnswer(Long id);

    Question addAnswerToQuestion(Long questionId, Long answerId);
}
