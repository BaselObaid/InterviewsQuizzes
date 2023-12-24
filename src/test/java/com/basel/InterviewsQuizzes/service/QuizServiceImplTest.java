package com.basel.InterviewsQuizzes.service;

import com.basel.InterviewsQuizzes.exception.quiz.QuizNotFoundException;
import com.basel.InterviewsQuizzes.model.entity.Quiz;
import com.basel.InterviewsQuizzes.model.mapper.QuestionMapper;
import com.basel.InterviewsQuizzes.repository.QuizRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
@SpringBootTest
@ActiveProfiles("test_quiz_service")
class QuizServiceImplTest {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;
    private static Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);

    @Test
    void addQuestionToQuiz() {
        Quiz quiz = checkQuizExistence(3);
        //QuestionDto questionDto = questionService.getQuestionById(6);
        logger.info("Quiz before adding question: {}", quiz.toString());
    }

    private Quiz checkQuizExistence(int id){
        return quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("this quiz is not Exist"));
    }
}