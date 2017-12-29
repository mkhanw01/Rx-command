package com.waseem.command.validation.validator;

import com.waseem.command.entity.Product;
import com.waseem.command.repository.ProductRepository;
import com.waseem.command.validation.ProductMustExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by khan on 12/29/17.
 */
@Component
public class ProductMustExistsValidator implements ConstraintValidator<ProductMustExists, String> {

  @Autowired
  private ProductRepository productRepository;

  @Override

  public void initialize(ProductMustExists constraintAnnotation) {
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    Product product = productRepository.findById(value).block();
    return product != null;
  }
}
