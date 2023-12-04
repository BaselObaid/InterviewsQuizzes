package com.basel.InterviewsQuizzes.repository;

import com.basel.InterviewsQuizzes.model.entity.Question;
import com.basel.InterviewsQuizzes.model.pojo.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findQuestionByDifficulty(Difficulty difficulty);
}
