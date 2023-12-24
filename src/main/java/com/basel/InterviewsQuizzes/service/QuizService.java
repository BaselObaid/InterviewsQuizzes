package com.basel.InterviewsQuizzes.service;

import com.basel.InterviewsQuizzes.model.dto.QuizDto;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface QuizService {

    @Transactional
    void addQuiz(QuizDto quizDto);
    @Transactional
    void addQuestionToQuiz(long QuestionId, int QuizId);
    @Transactional
    void updateQuiz(QuizDto quizDto, int id);
    @Transactional
    void deleteQuiz(int id);
    @Transactional
    void deleteQuestionFromQuiz(int QuizId, long QuestionId);
    QuizDto getQuizById(int id);
    double getTotalDegreesOfQuiz(int id);
    Page<QuizDto> getAllQuizzes(int page, int size, String sortField, String sortOrder);
    Page<QuizDto> searchByCategory(String category, int page, int size, String sortField, String sortOrder);
    Page<QuizDto> searchByTitle(String title, int page, int size, String sortField, String sortOrder);

}
