package com.waseem.command.validation.validator;

import com.waseem.command.repository.CartRepository;
import com.waseem.command.validation.CartMustNotExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by khan on 12/29/17.
 */
@Component
public class CartMustNotExistsValidator implements ConstraintValidator<CartMustNotExists, String> {

  @Autowired
  private CartRepository cartRepository;

  @Override
  public void initialize(CartMustNotExists constraintAnnotation) {

  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    return s == null || cartRepository.existsById(s).block() == Boolean.FALSE;
  }
}
