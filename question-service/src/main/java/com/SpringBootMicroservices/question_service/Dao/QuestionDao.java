package com.SpringBootMicroservices.question_service.Dao;

import com.SpringBootMicroservices.question_service.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String category);

    @Query(value = "Select q.id from question q where q.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery =true )
    List<Integer> findRandomQuestionsByCategory(String category, Integer numQ);
}
