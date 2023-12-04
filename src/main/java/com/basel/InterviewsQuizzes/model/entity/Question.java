package com.basel.InterviewsQuizzes.model.entity;

import com.basel.InterviewsQuizzes.model.pojo.Difficulty;
import com.basel.InterviewsQuizzes.model.pojo.Option;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

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
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Column(name="time_limit")
    private Duration timeDuration;


    @Embedded
    private Option options;


    @ManyToMany(mappedBy = "questions")
    private Set<Quiz> quizzes = new HashSet<>();

}
