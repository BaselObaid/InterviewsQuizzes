package com.basel.InterviewsQuizzes.model.mapper;

import com.basel.InterviewsQuizzes.model.dto.QuestionDto;
import com.basel.InterviewsQuizzes.model.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;
@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question questionDtoToQuestion(QuestionDto questionDto);

    QuestionDto questionToQuestionDto(Question question);

    void updateQuestionFromDto(QuestionDto questionDto, @MappingTarget Question question);

    List<QuestionDto> ListQuestionsToListQuestionDto(List<Question> questions);


}
