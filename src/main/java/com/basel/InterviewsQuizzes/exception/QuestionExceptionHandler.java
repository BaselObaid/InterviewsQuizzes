package com.basel.InterviewsQuizzes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class QuestionExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<Map<String, List<String>>> handleValidationFieldsExceptions(Exception exception) {
        List<String> errors = new ArrayList<>();
        if(exception instanceof HttpMessageNotReadableException){
            errors = Collections.singletonList("Invalid request payload. Please check your request body:" + exception.toString());
        }else if(exception instanceof MethodArgumentNotValidException){
            errors = ((MethodArgumentNotValidException)exception).getBindingResult().getFieldErrors()
                    .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        }
        return new ResponseEntity<>(getErrorsMap(errors), HttpStatus.BAD_REQUEST);
    }
    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }



    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleQuestionNotFoundException(QuestionNotFoundException exception) {
        Map<String, String> errorResponse = Collections.singletonMap("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(QuestionAssociatedWithQuizException.class)
    public ResponseEntity<Map<String, String>> handleQuestionAssociatedWithQuizException(QuestionAssociatedWithQuizException exception) {
        Map<String, String> errorResponse = Collections.singletonMap("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleTypeMismatchException(MethodArgumentTypeMismatchException exception){
        Map<String, String> errorResponse = Collections.singletonMap("error", "Parameter '" + exception.getName() + "' must be of type " + exception.getRequiredType().getSimpleName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception exception) {
        Map<String, String> errorResponse = Collections.singletonMap("error", "Internal Server Error:"+exception.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }


    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, String>> handleValidationMethodsException(HandlerMethodValidationException exception) {
        Map<String, String> errorResponse = Collections.singletonMap("error", "you pass not valid value");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
