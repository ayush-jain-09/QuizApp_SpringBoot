package com.ayush.quizapp.dao;

import com.ayush.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {
    Quiz findByQuizId(String quizId);
}
