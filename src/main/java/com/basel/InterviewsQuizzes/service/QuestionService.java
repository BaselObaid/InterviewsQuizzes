package com.basel.InterviewsQuizzes.service;

import com.basel.InterviewsQuizzes.exception.question.QuestionAssociatedWithQuizException;
import com.basel.InterviewsQuizzes.model.dto.QuestionDto;
import com.basel.InterviewsQuizzes.model.pojo.Option;
import org.springframework.data.domain.Page;

public interface QuestionService {

    void AddQuestion(QuestionDto questionDto);
    void deleteQuestion(long id) throws QuestionAssociatedWithQuizException;
    void updateQuestion(QuestionDto questionDto, long id);
    void updateQuestionDegree(double degree, long id);
    Page<QuestionDto> getAllQuestions(int page, int size, String sortField, String sortOrder);
    QuestionDto getQuestionById(long id);
    Page<QuestionDto> getQuestionByQuestionText(String qText, int page, int size, String sortField, String sortOrder);
    void updateOptionsInQuestion(Option options, long id);
    Page<QuestionDto> getQuestionsByDifficulty(String difficulty, int page, int size, String sortField, String sortOrder);

}
