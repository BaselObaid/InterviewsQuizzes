package com.basel.InterviewsQuizzes.repository;

import com.basel.InterviewsQuizzes.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findQuestionByDifficulty(String difficulty);

    List<Question> findByQuestionTextContaining(String questionText);

    @Query(nativeQuery = true, value = "SELECT * FROM questions_quizzes WHERE question_id = :questionId")
    Long checkQuestionFromQuizzes(@Param("questionId") long questionId);


}
