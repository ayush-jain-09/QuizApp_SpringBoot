package com.ayush.quizapp.model;

import lombok.Data;

@Data
public class QuizRequest {
    private String quizId;
    private String quizTitle;  // This is treated as category
    private int numberOfQuestions;
}
