package com.waseem.command.model.service;

import com.waseem.command.validation.CartMustExists;
import com.waseem.command.validation.ProductMustExists;
import com.waseem.command.validation.ProductMustExistsInCart;
import com.waseem.command.validation.ProductQuantityMustEnough;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Created by khan on 12/29/17.
 */
@Data
@Builder
@ProductQuantityMustEnough(path = "quantity")
@ProductMustExistsInCart(path = "productId")
public class UpdateProductInCartRequest
    implements ServiceRequest, ProductMustExistsInCart.ProductInCart,
    ProductQuantityMustEnough.ProductQuantityUpdate {

  @NotBlank
  @CartMustExists
  private String cartId;

  @NotBlank
  @ProductMustExists
  private String productId;

  @Min(0)
  @Max(20)
  private Integer quantity;
}
