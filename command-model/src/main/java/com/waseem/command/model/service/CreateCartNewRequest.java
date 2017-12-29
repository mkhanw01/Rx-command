package com.waseem.command.model.service;

import com.waseem.command.validation.CartMustNotExists;
import lombok.Builder;
import lombok.Data;

/**
 * Created by khan on 12/29/17.
 */
@Data
@Builder
public class CreateCartNewRequest implements ServiceRequest {

  @CartMustNotExists
  private String cartId;
}
