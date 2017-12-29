package com.waseem.command.service.command.impl;

import com.waseem.command.entity.Cart;
import com.waseem.command.model.service.UpdateProductInCartRequest;
import com.waseem.command.repository.CartRepository;
import com.waseem.command.service.AbstractCommand;
import com.waseem.command.service.command.UpdateProductInCartCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Created by khan on 12/29/17.
 */
@Component
public class UpdateProductCartCommandImpl extends AbstractCommand<Cart, UpdateProductInCartRequest>
    implements UpdateProductInCartCommand {

  @Autowired
  private CartRepository cartRepository;

  @Override
  public Mono<Cart> doExecute(UpdateProductInCartRequest request) {
    return this.cartRepository.findById(request.getCartId())
        .map(cart -> updateCartItemQuantity(cart, request));
  }

  private Cart updateCartItemQuantity(Cart cart, UpdateProductInCartRequest request) {
    cart.getItems().stream().filter(cartItem -> cartItem.getId().equals(request.getProductId()))
        .forEach(cartItem -> cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity()));
    return cart;
  }
}
