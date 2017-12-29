package com.waseem.command.model.service;

import com.waseem.command.validation.CartMustExists;
import com.waseem.command.validation.ProductMustExists;
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
public class AddProductToCartRequest implements ServiceRequest, ProductQuantityMustEnough
    .ProductQuantity {

  @NotBlank
  @CartMustExists
  private String cartId;

  @NotBlank
  @ProductMustExists
  private String productId;

  @Min(0)
  @Max(10)
  private Integer quantity;
}
