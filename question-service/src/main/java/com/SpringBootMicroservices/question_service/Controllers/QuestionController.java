package com.SpringBootMicroservices.question_service.Controllers;

import com.SpringBootMicroservices.question_service.Models.Question;
import com.SpringBootMicroservices.question_service.Models.QuestionWrapper;
import com.SpringBootMicroservices.question_service.Models.Response;
import com.SpringBootMicroservices.question_service.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping(value = "/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping(value = "/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable("category") String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping(value = "/addQuestion",consumes = "application/json")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PutMapping(value = "/updateQuestion/{question}")
    public ResponseEntity<Question> updateQuestion(@PathVariable("question") int question, @RequestBody Question questionBody){
        Optional<Question> question1 =  questionService.findById(question);
        Question updateQuestion = null;
        if(question1 != null){
            updateQuestion=  questionService.updateQuestion(questionBody);
            return new ResponseEntity<>(updateQuestion,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(updateQuestion, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/deleteQuestionById/{question}")
    public ResponseEntity deleteQuestionById(@PathVariable("question") int question){
        questionService.deleteQuestionById(question);
        return new ResponseEntity("Question deleted",HttpStatus.OK);
    }

    // Microservices New methods
    @GetMapping(value = "/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category,@RequestParam Integer numOfQuestions){
        return questionService.getQuestionsForQuiz(category,numOfQuestions);
    }

    @PostMapping(value = "getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsforQuizMicroserviceById(
            @RequestBody List<Integer> questiondIds){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsforQuizMicroservice(questiondIds);
    }

    @PostMapping(value = "getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }

}
