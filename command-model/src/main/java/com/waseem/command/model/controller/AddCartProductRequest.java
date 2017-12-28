package com.waseem.command.model.controller;

import lombok.Builder;
import lombok.Data;

/**
 * Created by khan on 12/28/17.
 */
@Data
@Builder
public class AddCartProductRequest {
  private String productId;
  private Integer quantity;
}
