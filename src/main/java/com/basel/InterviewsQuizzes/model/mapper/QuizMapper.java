package com.basel.InterviewsQuizzes.model.mapper;

import com.basel.InterviewsQuizzes.model.dto.QuizDto;
import com.basel.InterviewsQuizzes.model.entity.Quiz;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface QuizMapper {
    Quiz quizDtoToQuiz(QuizDto quizDto);

    QuizDto quizToQuizDto(Quiz quiz);

    Set<Quiz> setQuizDtoToSetQuiz(Set<QuizDto> quizDtos);

}
