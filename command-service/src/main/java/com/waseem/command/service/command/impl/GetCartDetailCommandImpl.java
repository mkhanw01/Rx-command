package com.waseem.command.service.command.impl;

import com.waseem.command.entity.Cart;
import com.waseem.command.model.service.GetCartDetailRequest;
import com.waseem.command.repository.CartRepository;
import com.waseem.command.service.AbstractCommand;
import com.waseem.command.service.command.GetCartDetailCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Created by khan on 12/29/17.
 */
@Component
public class GetCartDetailCommandImpl extends AbstractCommand<Cart, GetCartDetailRequest>
    implements GetCartDetailCommand {

  @Autowired
  private CartRepository cartRepository;

  @Override
  public Mono<Cart> doExecute(GetCartDetailRequest request) {
    return this.cartRepository.findById(request.getCartId());
  }
}
