package com.basel.InterviewsQuizzes.handler;

import com.basel.InterviewsQuizzes.exception.question.QuestionAssociatedWithQuizException;
import com.basel.InterviewsQuizzes.exception.question.QuestionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Map;

/**
 * Exception handler for specific exceptions related to question.
 */
@RestControllerAdvice
public class QuestionExceptionHandler implements ErrorsMapCreator {


    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleQuestionNotFoundException(QuestionNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createErrorMap(exception.getMessage()));
    }

    @ExceptionHandler(QuestionAssociatedWithQuizException.class)
    public ResponseEntity<Map<String, String>> handleQuestionAssociatedWithQuizException(QuestionAssociatedWithQuizException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createErrorMap(exception.getMessage()));
    }


    @Override
    public Map<String, String> createErrorMap(String message) {
        Map<String, String> errorResponse = Collections.singletonMap("error", message);
        return errorResponse;
    }
}
