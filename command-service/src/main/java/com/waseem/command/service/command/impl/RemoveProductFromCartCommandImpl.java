package com.waseem.command.service.command.impl;

import com.waseem.command.entity.Cart;
import com.waseem.command.entity.CartItem;
import com.waseem.command.model.service.RemoveProductFromCartRequest;
import com.waseem.command.repository.CartRepository;
import com.waseem.command.service.AbstractCommand;
import com.waseem.command.service.command.RemoveProductFromCartCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;

/**
 * Created by khan on 12/29/17.
 */
@Component
public class RemoveProductFromCartCommandImpl
    extends AbstractCommand<Cart, RemoveProductFromCartRequest>
    implements RemoveProductFromCartCommand {

  @Autowired
  private CartRepository cartRepository;

  @Override
  public Mono<Cart> doExecute(RemoveProductFromCartRequest request) {
    return this.cartRepository.findById(request.getCartId())
        .map(cart -> findCartItemAndRemoveIt(cart, request.getProductId()))
        .flatMap(cart -> cartRepository.save(cart));

  }

  private Cart findCartItemAndRemoveIt(Cart cart, @NotBlank String productId) {
    CartItem cartItem = findItemInCart(cart, productId);
    return removeItemFromCart(cart, cartItem);
  }

  private Cart removeItemFromCart(Cart cart, CartItem cartItem) {
    cart.getItems().remove(cartItem);
    return cart;
  }

  private CartItem findItemInCart(Cart cart, @NotBlank String productId) {
    return cart.getItems().stream().filter(cartItem -> cartItem.getId().equals(productId))
        .findFirst().get();
  }
}
