package com.waseem.command.validation.validator;

import com.waseem.command.entity.Product;
import com.waseem.command.repository.ProductRepository;
import com.waseem.command.validation.ProductQuantityMustEnough;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by khan on 12/29/17.
 */
@Component
public class ProductQuantityMustEnoughValidator implements
    ConstraintValidator<ProductQuantityMustEnough, ProductQuantityMustEnough.ProductQuantity> {
  @Autowired
  private ProductRepository productRepository;

  @Override
  public void initialize(ProductQuantityMustEnough constraintAnnotation) {

  }

  @Override
  public boolean isValid(ProductQuantityMustEnough.ProductQuantity value,
      ConstraintValidatorContext context) {
    if (value == null || value.getProductId() == null || value.getQuantity() == null) {
      return true;
    }
    Product product = this.productRepository.findById(value.getProductId()).block();
    if (isProductNotExists(product)) {
      return true;
    }
    return isStockEnough(product, value.getQuantity());
  }

  private boolean isStockEnough(Product product, Integer quantity) {
    return product.getStock() >= quantity;
  }

  private boolean isProductNotExists(Product product) {
    return product == null;
  }
}
