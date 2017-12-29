package com.waseem.command.model.controller;

import lombok.Builder;
import lombok.Data;

/**
 * Created by khan on 12/29/17.
 */
@Data
@Builder
public class CartRemoveProductRequest {

  private String productId;
}
