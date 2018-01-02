package com.waseem.command.controller;

import com.waseem.command.entity.Cart;
import com.waseem.command.model.controller.AddCartProductRequest;
import com.waseem.command.model.controller.CartUpdateProductRequest;
import com.waseem.command.model.controller.Response;
import com.waseem.command.model.service.AddProductToCartRequest;
import com.waseem.command.model.service.CreateCartNewRequest;
import com.waseem.command.model.service.GetCartDetailRequest;
import com.waseem.command.model.service.RemoveProductFromCartRequest;
import com.waseem.command.model.service.UpdateProductInCartRequest;
import com.waseem.command.service.ServiceExecutor;
import com.waseem.command.service.command.AddProductToCartCommand;
import com.waseem.command.service.command.CreateCartNewCommand;
import com.waseem.command.service.command.GetCartDetailCommand;
import com.waseem.command.service.command.RemoveProductFromCartCommand;
import com.waseem.command.service.command.UpdateProductInCartCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping(value = "/{cartId}/add-product",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Response<Cart>> addProduct(@PathVariable("cartId") String cartId,
      @RequestBody AddCartProductRequest productRequest) {
    AddProductToCartRequest request =
        AddProductToCartRequest.builder().cartId(cartId).productId(productRequest.getProductId())
            .quantity(productRequest.getQuantity()).build();

    return this.serviceExecutor.execute(AddProductToCartCommand.class, request).map(Response::ok)
        .subscribeOn(Schedulers.elastic());
  }

  @GetMapping(value = "/{cartId}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Response<Cart>> findByCartId(@PathVariable("cartId") String cartId) {
    GetCartDetailRequest detailRequest = GetCartDetailRequest.builder().cartId(cartId).build();
    return this.serviceExecutor.execute(GetCartDetailCommand.class, detailRequest).map(Response::ok)
        .subscribeOn(Schedulers.elastic());
  }

  @DeleteMapping(value = "/{cartId}/{productId}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Response<Cart>> removeByCartId(@PathVariable("cartId") String cartId,
      @PathVariable("productId") String productId) {
    RemoveProductFromCartRequest request =
        RemoveProductFromCartRequest.builder().cartId(cartId).productId(productId).build();

    return this.serviceExecutor.execute(RemoveProductFromCartCommand.class, request)
        .map(Response::ok).subscribeOn(Schedulers.elastic());
  }

  @PutMapping(value = "/{cartId}/update-product",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Response<Cart>> updateCart(@PathVariable("cartId") String cartId,
      CartUpdateProductRequest productRequest) {
    UpdateProductInCartRequest request =
        UpdateProductInCartRequest.builder().cartId(cartId).productId(productRequest.getProductId())
            .quantity(productRequest.getQuantity()).build();

    return this.serviceExecutor.execute(UpdateProductInCartCommand.class, request).map(Response::ok)
        .subscribeOn(Schedulers.elastic());
  }

}
