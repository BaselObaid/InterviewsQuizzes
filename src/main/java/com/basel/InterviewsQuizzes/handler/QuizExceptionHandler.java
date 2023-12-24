package com.basel.InterviewsQuizzes.handler;

import com.basel.InterviewsQuizzes.exception.quiz.QuestionDuplicatedException;
import com.basel.InterviewsQuizzes.exception.quiz.QuizNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Map;

@RestControllerAdvice
public class QuizExceptionHandler implements ErrorsMapCreator{


    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleQuizNotFoundException(QuizNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createErrorMap(exception.getMessage()));
    }

    @ExceptionHandler(QuestionDuplicatedException.class)
    public ResponseEntity<Map<String, String>> handleQuestionDuplicatedException(QuestionDuplicatedException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createErrorMap(exception.getMessage()));
    }

    @Override
    public Map<String, String> createErrorMap(String message) {
        Map<String, String> errorResponse = Collections.singletonMap("error", message);
        return errorResponse;
    }
}
