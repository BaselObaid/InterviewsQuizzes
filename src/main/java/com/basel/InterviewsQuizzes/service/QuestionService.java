package com.basel.InterviewsQuizzes.service;

import com.basel.InterviewsQuizzes.exception.QuestionAssociatedWithQuizException;
import com.basel.InterviewsQuizzes.model.dto.QuestionDto;
import com.basel.InterviewsQuizzes.model.pojo.Option;

import java.util.List;

public interface QuestionService {

    void AddQuestion(QuestionDto questionDto);
    void deleteQuestion(long id) throws QuestionAssociatedWithQuizException;
    void updateQuestion(QuestionDto questionDto, long id);
    List<QuestionDto> getAllQuestions();
    QuestionDto getQuestionById(long id);
    List<QuestionDto> getQuestionByQuestionText(String qText);
    List<QuestionDto> getQuestionsByDifficulty(String difficulty);
    void updateOptionsInQuestion(Option options, long id);
}
