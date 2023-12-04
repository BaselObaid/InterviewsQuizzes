package com.basel.InterviewsQuizzes.exception;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(String s) {
        super(s);
    }
}
