package com.basel.InterviewsQuizzes.model.dto;

import com.basel.InterviewsQuizzes.model.pojo.Difficulty;
import com.basel.InterviewsQuizzes.model.pojo.Option;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.Duration;

@Data
@Builder
public class QuestionDto {
    private final long id;
    @NotEmpty
    private final String questionText;
    @NotEmpty
    private final String solution;
    private final Difficulty difficulty;
    private final Duration timeDuration;
    private final Option options;
}
