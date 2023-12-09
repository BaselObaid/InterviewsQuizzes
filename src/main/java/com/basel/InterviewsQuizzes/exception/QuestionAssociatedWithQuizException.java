package com.basel.InterviewsQuizzes.exception;

public class QuestionAssociatedWithQuizException extends RuntimeException {
    public QuestionAssociatedWithQuizException(String reason) {
        super(reason);
    }
}
