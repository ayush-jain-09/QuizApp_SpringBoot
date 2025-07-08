package com.ayush.quizapp.controller;

import com.ayush.quizapp.model.*;
import com.ayush.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ayush.quizapp.dao.QuestionDao;
import com.ayush.quizapp.dao.QuizDao;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;
    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private QuizDao quizDao;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // 1. Fetch all questions
    @GetMapping("/allQuestions")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    // 2. Fetch questions by category
    @GetMapping("/category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    // 3. Add question
    @PostMapping("/add")
    public String addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
        return "success";
    }
    // delete
    @DeleteMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Integer id) {
        questionService.deleteQuestion(id);
        return "deleted successfully";
    }


    //creating quiz by providing category and number in payload(quiz id, category, number of question)

    @PostMapping("/generate")
public List<QuestionWrapper> generateQuiz(@RequestBody QuizRequest request) {



        List<Question> questions = questionDao.findRandomQuestionsByCategory(request.getQuizTitle(), request.getNumberOfQuestions());

    // Save quiz to DB
    Quiz quiz =new Quiz(null, request.getQuizId(), request.getQuizTitle(), questions);
        quizDao.save(quiz);


        quizDao.save(quiz);

    // Return safe questions
    return questions.stream().map(q -> new QuestionWrapper(
            q.getId(),
            q.getQuestionTitle(),
            q.getOption1(),
            q.getOption2(),
            q.getOption3(),
            q.getOption4()
    )).collect(Collectors.toList());
}

//user submits answer provding question id and response
    @PostMapping("/submit/{quizId}")
    public String submitQuiz(
            @PathVariable String quizId,
            @RequestBody List<Response> responses) {

        Quiz quiz = quizDao.findByQuizId(quizId);
        List<Question> questions = quiz.getQuestions();

        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            String correct = questions.get(i).getRightAnswer().trim();
            String given = responses.get(i).getResponse().trim();

            if (correct.equalsIgnoreCase(given)) {
                score++;
            }
        }

        return "Your score is " + score + " out of " + questions.size();
    }


}
