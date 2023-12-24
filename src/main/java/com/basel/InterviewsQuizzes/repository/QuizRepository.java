package com.basel.InterviewsQuizzes.repository;

import com.basel.InterviewsQuizzes.model.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    boolean existsByIdAndQuestions_Id(int quizId, long questionId);
    Page<Quiz> findQuizByCategory(String category, Pageable pageable);
    Page<Quiz> findQuizByTitleContaining(String title, Pageable pageable);
}