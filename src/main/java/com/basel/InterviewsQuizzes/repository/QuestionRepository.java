package com.basel.InterviewsQuizzes.repository;

import com.basel.InterviewsQuizzes.model.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Page<Question> findQuestionByDifficulty(String difficulty, Pageable pageable);

    Page<Question> findByQuestionTextContaining(String questionText, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM questions_quizzes WHERE question_id = :questionId")
    Long checkQuestionFromQuizzes(@Param("questionId") long questionId);

}
