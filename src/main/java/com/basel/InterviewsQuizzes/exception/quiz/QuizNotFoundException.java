package com.basel.InterviewsQuizzes.exception.quiz;

public class QuizNotFoundException extends RuntimeException {
    public QuizNotFoundException(String message) {
        super(message);
    }
}
