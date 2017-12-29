package com.waseem.command.validation.validator;

import com.waseem.command.entity.Cart;
import com.waseem.command.repository.CartRepository;
import com.waseem.command.validation.ProductMustExistsInCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by khan on 12/29/17.
 */
@Component
public class ProductMustExistsInCartValidator
    implements ConstraintValidator<ProductMustExistsInCart, ProductMustExistsInCart.ProductInCart> {

  @Autowired
  private CartRepository cartRepository;

  @Override
  public void initialize(ProductMustExistsInCart constraintAnnotation) {

  }

  @Override
  public boolean isValid(ProductMustExistsInCart.ProductInCart value,
      ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    Cart cart = this.cartRepository.findById(value.getCartId()).block();
    if (cart == null) {
      return true;
    }
    if (cart.getItems() == null || cart.getItems().isEmpty()) {
      return false;
    }
    return cart.getItems().stream()
        .anyMatch(cartItem -> cartItem.getId().equals(value.getProductId()));
  }
}
