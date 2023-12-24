package com.basel.InterviewsQuizzes.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class CategoryValidator implements ConstraintValidator<CategoryValidationAnnotation, String> {

    private final List<String> validCategoryValues = Arrays.asList("JAVA", "PYTHON", "C#", "NODEJS", "GO", "C++");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return validCategoryValues.contains(value);
    }
}
