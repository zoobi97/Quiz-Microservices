package com.SpringBootMicroservices.quiz_service.Feign;

import com.SpringBootMicroservices.quiz_service.Models.QuestionWrapper;
import com.SpringBootMicroservices.quiz_service.Models.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping(value = "question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category,@RequestParam String title,
                                                             @RequestParam Integer numOfQuestions);

    @PostMapping(value = "question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsforQuizMicroserviceById(
            @RequestBody List<Integer> questiondIds);

    @PostMapping(value = "question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
