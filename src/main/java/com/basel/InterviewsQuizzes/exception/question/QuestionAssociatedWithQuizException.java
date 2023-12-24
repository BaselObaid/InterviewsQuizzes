package com.basel.InterviewsQuizzes.exception.question;

public class QuestionAssociatedWithQuizException extends RuntimeException {
    public QuestionAssociatedWithQuizException(String reason) {
        super(reason);
    }
}
