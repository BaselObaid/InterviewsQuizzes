package com.basel.InterviewsQuizzes.handler;

import com.basel.InterviewsQuizzes.exception.global.SortValueException;
import org.springframework.data.mapping.PropertyReferenceException;
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

/**
 * Global exception handler for project-related exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler implements ErrorsMapCreator{
    /**
     * Exception handler for handling validation-related exceptions.
     *
     * @param exception The exception to handle.
     * @return ResponseEntity with a map of validation errors.
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<Map<String, List<String>>> handleValidationFieldsExceptions(Exception exception) {
        List<String> errors = new ArrayList<>();
        if(exception instanceof HttpMessageNotReadableException){
            errors = Collections.singletonList("Invalid request payload. Please check your request body:" + exception.toString());
        }else if(exception instanceof MethodArgumentNotValidException){
            errors = ((MethodArgumentNotValidException)exception).getBindingResult().getFieldErrors()
                    .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        }
        return new ResponseEntity<>(createErrorsMap(errors), HttpStatus.BAD_REQUEST);
    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, String>> handleGenericException(Exception exception) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorMap("Internal Server Error: "+exception.toString()));
//    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleTypeMismatchException(MethodArgumentTypeMismatchException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createErrorMap("Parameter '" + exception.getName() + "' must be of type " + exception.getRequiredType().getSimpleName()));
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, String>> handleValidationMethodsException(HandlerMethodValidationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createErrorMap("you pass not valid value: " + exception.getMessage()));
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<Map<String, String>> handlePropertyReferenceException(PropertyReferenceException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createErrorMap("not valid property: " + exception.getMessage()));
    }

    @ExceptionHandler(SortValueException.class)
    public ResponseEntity<Map<String, String>> handleSortValueException(SortValueException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createErrorMap(exception.getMessage()));
    }

    public Map<String, String> createErrorMap(String message){
        Map<String, String> errorResponse = Collections.singletonMap("error", message);
        return errorResponse;
    }


     private Map<String, List<String>> createErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
