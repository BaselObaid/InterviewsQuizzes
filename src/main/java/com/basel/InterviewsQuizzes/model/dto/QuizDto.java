package com.basel.InterviewsQuizzes.model.dto;

import com.basel.InterviewsQuizzes.validation.CategoryValidationAnnotation;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuizDto {
    private final int id;
    @NotBlank
    private final String title;
    @CategoryValidationAnnotation(message = "only use JAVA, PYTHON, C#, NODEJS, GO or C++ category")
    private final String category;
    private final List<QuestionDto> questions;
    private final double totalDegrees;
}
