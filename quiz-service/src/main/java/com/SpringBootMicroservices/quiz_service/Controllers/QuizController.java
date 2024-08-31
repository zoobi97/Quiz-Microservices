package com.SpringBootMicroservices.quiz_service.Controllers;

import com.SpringBootMicroservices.quiz_service.Models.QuestionWrapper;
import com.SpringBootMicroservices.quiz_service.Models.QuizDto;
import com.SpringBootMicroservices.quiz_service.Models.Response;
import com.SpringBootMicroservices.quiz_service.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
       return quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions(),quizDto.getTitle());
    }

    @GetMapping(value = "/getQuizQuestionsById/{quiz}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable("quiz") Integer quizId){
        return quizService.getQuizQuestionsById(quizId);
    }

    @PostMapping(value = "/submitQuiz/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable("id") Integer id,
                                              @RequestBody List<Response> responses){
        return quizService.calculateResult(id,responses);
    }
}
