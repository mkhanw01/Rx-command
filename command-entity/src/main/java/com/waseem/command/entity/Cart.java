package com.waseem.command.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by khan on 12/28/17.
 */
@Data
@Builder
@Document
public class Cart {
  @Id
  private String id;

}
