package com.basel.InterviewsQuizzes.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class DifficultyValidator implements ConstraintValidator<DifficultyValidationAnnotation, String> {

    private final List<String> validDifficultyValues = Arrays.asList("EASY", "MEDIUM", "DIFFICULT");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return validDifficultyValues.contains(value);
    }
}
