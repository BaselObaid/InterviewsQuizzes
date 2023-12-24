package com.basel.InterviewsQuizzes.exception.quiz;

public class QuestionDuplicatedException extends RuntimeException{
    public QuestionDuplicatedException(String message){
        super(message);
    }
}
