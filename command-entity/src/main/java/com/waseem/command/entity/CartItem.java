package com.waseem.command.entity;

import lombok.Builder;
import lombok.Data;

/**
 * Created by khan on 12/28/17.
 */
@Data
@Builder
public class CartItem {
  private String id;
  private String name;
  private Long price;
  private Integer quantity;
}
