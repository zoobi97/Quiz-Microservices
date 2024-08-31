package com.SpringBootMicroservices.question_service.Services;

import com.SpringBootMicroservices.question_service.Dao.QuestionDao;
import com.SpringBootMicroservices.question_service.Models.Question;
import com.SpringBootMicroservices.question_service.Models.QuestionWrapper;
import com.SpringBootMicroservices.question_service.Models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao repository;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(repository.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        repository.save(question);
        return new ResponseEntity<>("Question added sucessfully",HttpStatus.CREATED);
    }

    public String deleteQuestionById(int question) {
        repository.deleteById(question);
        return "Question deleted";
    }

    public Optional<Question> findById(int question) {
        return repository.findById(question);
    }

    public Question updateQuestion(Question questionBody) {
        Question updated =  repository.save(questionBody);
        return updated;
    }

    //Micro services Method
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numOfQuestions) {

        List<Integer> questionList = repository.findRandomQuestionsByCategory(category,numOfQuestions);
        return new ResponseEntity<>(questionList, HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsforQuizMicroservice(List<Integer> questiondIds) {
        List<QuestionWrapper> questionWrapper = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer questionsV: questiondIds){
            questions.add(repository.findById(questionsV).get());
        }

        for(Question questionVariable: questions){
            QuestionWrapper questionWrapper1 = new QuestionWrapper();

            questionWrapper1.setId(questionVariable.getId());
            questionWrapper1.setQuestionTitle(questionVariable.getQuestionTitle());
            questionWrapper1.setOption1(questionVariable.getOption1());
            questionWrapper1.setOption2(questionVariable.getOption2());
            questionWrapper1.setOption3(questionVariable.getOption3());
            questionWrapper1.setOption4(questionVariable.getOption4());

            questionWrapper.add(questionWrapper1);
        }

        return new ResponseEntity<>(questionWrapper,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right=0;
        for(Response responseV: responses){
            Question question = repository.findById(responseV.getId()).get();
            if(responseV.getResponse().equals(question.getRightAnswer())){
                right++;
            }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
