package com.basel.InterviewsQuizzes.model.mapper;

import com.basel.InterviewsQuizzes.model.dto.QuestionDto;
import com.basel.InterviewsQuizzes.model.entity.Question;
import org.springframework.stereotype.Component;

@Component
public class UpdateMapper {
    public void updateQuestionFromDto(QuestionDto questionDto, Question question) {

        question.setQuestionText(questionDto.getQuestionText());
        question.setSolution(questionDto.getSolution());
        question.setDifficulty(questionDto.getDifficulty());
        question.setTimeDuration(questionDto.getTimeDuration());
        question.setOptions(questionDto.getOptions());
    }
}
