package com.waseem.command.model.service;

import com.waseem.command.validation.CartMustExists;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by khan on 12/29/17.
 */
@Data
@Builder
public class GetCartDetailRequest implements ServiceRequest{

  @NotBlank
  @CartMustExists
  private String cartId;
}
