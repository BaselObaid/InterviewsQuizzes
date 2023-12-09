package com.basel.InterviewsQuizzes.model.dto;

import com.basel.InterviewsQuizzes.model.pojo.Option;
import com.basel.InterviewsQuizzes.validation.DifficultyValidationAnnotation;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.Duration;

@Data
@Builder
public class QuestionDto {
    private final long id;
    @NotBlank(message = "the question must have a question text")
    private final String questionText;
    @NotBlank(message = "the question must have a solution")
    private final String solution;
    @DifficultyValidationAnnotation
    private final String difficulty;
    private final Duration timeDuration;
    private final Option options;
}
