package com.ayush.quizapp.dao;
import com.ayush.quizapp.model.Question;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question WHERE category = :category ORDER BY RANDOM() LIMIT :count", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(@Param("category") String category, @Param("count") int count);

}

