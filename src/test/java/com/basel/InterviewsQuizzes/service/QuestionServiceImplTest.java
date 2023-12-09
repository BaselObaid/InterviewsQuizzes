package com.basel.InterviewsQuizzes.service;

import com.basel.InterviewsQuizzes.exception.QuestionAssociatedWithQuizException;
import com.basel.InterviewsQuizzes.exception.QuestionNotFoundException;
import com.basel.InterviewsQuizzes.model.dto.QuestionDto;
import com.basel.InterviewsQuizzes.model.pojo.Option;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test_question_service")
class QuestionServiceImplTest {

    @Autowired
    private QuestionService questionService;

    private static Logger logger = LoggerFactory.getLogger(QuestionServiceImplTest.class);

    @Test
    void addQuestion() {
        Option options = Option.builder()
                .option1("ArrayList is a synchronized collection.")
                .option2("ArrayList cannot store objects of different data types.")
                .option3("ArrayList is a resizable array that is a fixed size.")
                .build();
        QuestionDto questionDto = QuestionDto.builder()
                .id(4L)
                .questionText("Which of the following statements is true about the ArrayList class in Java?")
                .solution(" Elements in an ArrayList are accessed using an index.")
                .options(options)
                .difficulty("MEDIUM")
                .timeDuration(Duration.ofSeconds(30))
                .build();
        questionService.AddQuestion(questionDto);
    }

    @Test
    void deleteQuestion() throws QuestionAssociatedWithQuizException {
        questionService.deleteQuestion(2L);
    }

    @Test
    void updateQuestion() {
        QuestionDto questionDto = QuestionDto.builder()
                .id(2L)
                .questionText("what is string pool in java?")
                .solution("it is a place in memory heap to store literal strings")
                .timeDuration(Duration.ofSeconds(30))
                .difficulty("MEDIUM")
                .build();
        questionService.updateQuestion(questionDto, 2L);
    }

    @Test
    void getAllQuestions() {
        List<QuestionDto> questionList = questionService.getAllQuestions();
        logger.info(questionList.toString());
    }

    @Test
    void getQuestionById() {
        try {
            QuestionDto questionDto = questionService.getQuestionById(2L);
            logger.info(questionDto.toString());
        }catch (QuestionNotFoundException exception){
            exception.getMessage();
        }
    }

    @Test
    void getQuestionByQuestionText() {

    }

    @Test
    void getQuestionsByDifficulty() {
        List<QuestionDto> questions = questionService.getQuestionsByDifficulty("EASY");
        logger.info(questions.toString());
    }

    @Test
    void updateOptionsInQuestion() {
        Option options = Option.builder()
                .option1("its a place in stack memory.")
                .option2("its a place in heap to store all strings")
                .option3("its a place in heap to store non literal strings")
                .build();
        questionService.updateOptionsInQuestion(options,2L);
    }
}