package com.waseem.command.validation;

import com.waseem.command.validation.validator.CartMustNotExistsValidator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by khan on 12/29/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = {CartMustNotExistsValidator.class})
@Documented
public @interface CartMustNotExists {
}
