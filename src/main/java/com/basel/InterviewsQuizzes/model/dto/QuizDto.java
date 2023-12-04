package com.basel.InterviewsQuizzes.model.dto;

import com.basel.InterviewsQuizzes.model.pojo.Category;
import lombok.Data;

import java.util.Set;

@Data
public class QuizDto {
    private final int id;
    private final String title;
    private final Category category;
    private final Set<QuestionDto> questions;
}
