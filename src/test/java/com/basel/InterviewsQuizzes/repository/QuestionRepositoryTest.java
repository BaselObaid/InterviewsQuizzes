package com.basel.InterviewsQuizzes.repository;

import com.basel.InterviewsQuizzes.model.pojo.Option;
import com.basel.InterviewsQuizzes.model.entity.Question;
import com.basel.InterviewsQuizzes.model.pojo.Difficulty;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;

@SpringBootTest
@ActiveProfiles("test")
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    private static final Logger logger = LoggerFactory.getLogger(QuestionRepositoryTest.class);


    private Option createOptions(){
        Option option = Option.builder()
                .option1("bla")
                .option2("blabla")
                .option3("blablabla")
                .build();
        return option;
    }




    @Test
    public void createNewQuestionWithOptions(){
        Question question = Question.builder()
                .questionText("what is java?")
                .solution("java is an object oriented programming and independent platform language")
                .difficulty(Difficulty.Easy)
                .timeDuration(Duration.ofSeconds(30))
                .options(createOptions())
                .build();

        questionRepository.save(question);
    }


    @Test
    public void findAll(){
        questionRepository.findAll().forEach(question -> logger.info(question.toString()));
    }

    @Test
    public void deleteQuestions(){
        questionRepository.deleteAll();
    }


}