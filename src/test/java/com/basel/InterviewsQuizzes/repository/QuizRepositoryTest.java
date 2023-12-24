package com.basel.InterviewsQuizzes.repository;

import com.basel.InterviewsQuizzes.model.entity.Quiz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test quiz")
class QuizRepositoryTest {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;



  //  @Test
//    public void createNewQuizWithoutQuestions(){
//        Quiz quiz = Quiz.builder()
//                .title("java quiz for beginners")
//                .category(Category.JAVA)
//                .build();
//        quizRepository.save(quiz);
//    }



    @Test
    public void addQuestionsToQuiz(){
        Quiz quiz = quizRepository.findById(1).orElse(null);
        if(quiz!=null){
            quiz.getQuestions().add(questionRepository.findById(1L).orElse(null));
            quizRepository.save(quiz);
        }
    }

}