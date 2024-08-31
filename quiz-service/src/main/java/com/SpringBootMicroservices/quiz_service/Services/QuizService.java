package com.SpringBootMicroservices.quiz_service.Services;

import com.SpringBootMicroservices.quiz_service.Dao.QuizDao;
import com.SpringBootMicroservices.quiz_service.Feign.QuizInterface;
import com.SpringBootMicroservices.quiz_service.Models.QuestionWrapper;
import com.SpringBootMicroservices.quiz_service.Models.Quiz;
import com.SpringBootMicroservices.quiz_service.Models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        try {
        List<Integer> questionList = quizInterface.getQuestionsForQuiz(category,title,numQ).getBody();

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionList(questionList);
            quizDao.save(quiz);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while creating the quiz", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(int quizId) {
        Quiz quiz = quizDao.findById(quizId).get();
        List<Integer> questionlist = quiz.getQuestionList();

        ResponseEntity<List<QuestionWrapper>> questions= quizInterface.getQuestionsforQuizMicroserviceById(questionlist);

        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}
