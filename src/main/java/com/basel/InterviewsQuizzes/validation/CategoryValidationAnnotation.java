package com.basel.InterviewsQuizzes.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CategoryValidator.class)
public @interface CategoryValidationAnnotation {
    public String message() default "you use non valid category";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
