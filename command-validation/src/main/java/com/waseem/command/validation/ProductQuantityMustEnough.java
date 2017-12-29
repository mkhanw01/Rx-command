package com.waseem.command.validation;

import com.waseem.command.validation.validator.ProductQuantityMustEnoughValidator;
import com.waseem.command.validation.validator.ProductQuantityUpdateMustEnoughValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by khan on 12/29/17.
 */
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ProductQuantityUpdateMustEnoughValidator.class,
    ProductQuantityMustEnoughValidator.class})
@Documented
public @interface ProductQuantityMustEnough {
  String message() default "ProductQuantityMustEnough";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String [] path() default {};

  interface ProductQuantity {

    String getProductId();

    Integer getQuantity();
  }

  interface ProductQuantityUpdate {
    String getCartId();

    String getProductId();

    Integer getQuantity();
  }

}
