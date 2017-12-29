package com.waseem.command.service.command.impl;

import com.waseem.command.entity.Cart;
import com.waseem.command.model.service.CreateCartNewRequest;
import com.waseem.command.repository.CartRepository;
import com.waseem.command.service.AbstractCommand;
import com.waseem.command.service.command.CreateCartNewCommand;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

/**
 * Created by khan on 12/29/17.
 */
public class CreateCartNewCommandImpl extends AbstractCommand<Cart, CreateCartNewRequest>
    implements CreateCartNewCommand {

  @Autowired
  private CartRepository cartRepository;

  @Override
  public Mono<Cart> doExecute(CreateCartNewRequest request) {
    Cart cart = newCartCreate(request.getCartId());
    return this.cartRepository.save(cart);
  }

  private Cart newCartCreate(String cartId) {
    return Cart.builder().id(cartId).build();
  }
}
