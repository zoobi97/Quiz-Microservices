package com.SpringBootMicroservices.quiz_service.Models;

import lombok.Data;

@Data
public class QuizDto {

    String categoryName,title;
    Integer numQuestions;
}
