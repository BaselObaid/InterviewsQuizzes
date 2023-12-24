package com.basel.InterviewsQuizzes.model.entity;

import com.basel.InterviewsQuizzes.model.pojo.Option;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "question", nullable = false)
    private String questionText;
    @Column(nullable = false)
    private String solution;

    private String difficulty;

    @Column(name="time_limit")
    private Duration timeDuration;


    @Embedded
    private Option options;

    @Min(value = 0, message = "question degree should be >= 0.0")
    private double degree;

    @ManyToMany(mappedBy = "questions")
    private List<Quiz> quizzes = new ArrayList<>();



}
