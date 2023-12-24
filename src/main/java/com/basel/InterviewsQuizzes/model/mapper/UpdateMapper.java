package com.basel.InterviewsQuizzes.model.mapper;

import com.basel.InterviewsQuizzes.model.dto.QuestionDto;
import com.basel.InterviewsQuizzes.model.dto.QuizDto;
import com.basel.InterviewsQuizzes.model.entity.Question;
import com.basel.InterviewsQuizzes.model.entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateMapper {

    @Autowired
    private QuestionMapper questionMapper;

    public void updateQuestionFromDto(QuestionDto questionDto, Question question) {
        question.setQuestionText(questionDto.getQuestionText());
        question.setSolution(questionDto.getSolution());
        question.setDifficulty(questionDto.getDifficulty());
        question.setTimeDuration(questionDto.getTimeDuration());
        question.setOptions(questionDto.getOptions());
    }

    public void updateQuizFromDto(QuizDto quizDto, Quiz quiz){
        quiz.setTitle(quizDto.getTitle());
        quiz.setCategory(quizDto.getCategory());
        quiz.setQuestions(questionMapper.listQuestionDtoToListQuestion(quizDto.getQuestions()));
        quiz.setTotalDegrees(quizDto.getTotalDegrees());
    }
}
