package com.waseem.command.model.service;

import com.waseem.command.validation.CartMustExists;
import com.waseem.command.validation.ProductMustExists;
import com.waseem.command.validation.ProductMustExistsInCart;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by khan on 12/29/17.
 */

@Data
@Builder
@ProductMustExistsInCart(path = "productId")
public class RemoveProductFromCartRequest implements ServiceRequest,
    ProductMustExistsInCart.ProductInCart{

  @NotBlank
  @CartMustExists
  private String cartId;

  @NotBlank
  @ProductMustExists
  private String productId;
}
