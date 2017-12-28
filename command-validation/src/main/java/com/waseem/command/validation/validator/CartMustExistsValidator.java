package com.waseem.command.validation.validator;

import com.waseem.command.repository.CartRepository;
import com.waseem.command.validation.CartMustExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by khan on 12/28/17.
 */
@Component
public class CartMustExistsValidator implements ConstraintValidator<CartMustExists, String> {

  @Autowired
  private CartRepository cartRepository;

  @Override
  public void initialize(CartMustExists constraintAnnotation) {
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if (s == null)
      return true;
    return this.cartRepository.existsById(s).block();
  }
}
