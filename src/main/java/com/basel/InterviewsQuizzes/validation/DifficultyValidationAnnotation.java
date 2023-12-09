package com.basel.InterviewsQuizzes.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DifficultyValidator.class)
public @interface DifficultyValidationAnnotation {

    public String message() default "the difficulty should be EASY, MEDIUM or DIFFICULT";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
