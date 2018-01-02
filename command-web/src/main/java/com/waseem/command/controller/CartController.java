package com.waseem.command.controller;

import com.waseem.command.entity.Cart;
import com.waseem.command.model.controller.Response;
import com.waseem.command.model.service.CreateCartNewRequest;
import com.waseem.command.service.ServiceExecutor;
import com.waseem.command.service.command.CreateCartNewCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Created by khan on 1/2/18.
 */
@RestController
@RequestMapping(value = "/cart")
public class CartController {

  @Autowired
  ServiceExecutor serviceExecutor;
  @PostMapping(value = "/{cartId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Response<Cart>> create (@PathVariable("cartId") String cartId) {
    CreateCartNewRequest newRequest = CreateCartNewRequest.builder()
        .cartId(cartId).build();

    return this.serviceExecutor.execute(CreateCartNewCommand.class, newRequest)
        .map(Response::ok)
        .subscribeOn(Schedulers.elastic());
  }
}
