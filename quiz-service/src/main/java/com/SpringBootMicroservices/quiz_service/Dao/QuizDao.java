package com.SpringBootMicroservices.quiz_service.Dao;

import com.SpringBootMicroservices.quiz_service.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer> {

}
