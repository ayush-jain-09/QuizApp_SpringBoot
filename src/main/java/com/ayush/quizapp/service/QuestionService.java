package com.ayush.quizapp.service;
import com.ayush.quizapp.model.Question;
import com.ayush.quizapp.dao.QuestionDao;
import java.util.List;
import com.ayush.quizapp.dao.QuizDao;
import com.ayush.quizapp.model.Quiz;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }
    public void addQuestion(Question question) {
        questionDao.save(question);
    }
    public void deleteQuestion(Integer id) {
        questionDao.deleteById(id);
    }

 //for quiz

    @Autowired
    private QuizDao quizDao;

    public Quiz createAndSaveQuiz(String title, int count) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(title, count);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        return quizDao.save(quiz); // saves to DB
    }


}

