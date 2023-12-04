package com.basel.InterviewsQuizzes.model.mapper;

import com.basel.InterviewsQuizzes.model.entity.Quiz;
import com.basel.InterviewsQuizzes.model.dto.QuizDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface QuizMapper {
    Quiz quizDtoToQuiz(QuizDto quizDto);

    QuizDto quizToQuizDto(Quiz quiz);


}
