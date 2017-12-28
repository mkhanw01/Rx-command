package com.waseem.command.validation;

import com.waseem.command.validation.validator.CartMustExistsValidator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by khan on 12/28/17.
 */
@Target({TYPE,ANNOTATION_TYPE,METHOD,FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {CartMustExistsValidator.class})
@Documented
public @interface CartMustExists {
}
