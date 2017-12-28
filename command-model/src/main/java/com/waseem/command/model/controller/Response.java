package com.waseem.command.model.controller;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by khan on 12/28/17.
 */
@Data
@Builder
public class Response<T> {

  private Integer code;
  private String status;
  private T data;

  private Map<String, List<String>> errors;

  public void addError(String key, String error) {
    initializeErrorsIfNull();
    initializeListIfNull(key);
    errors.get(key).add(error);
  }

  private void initializeListIfNull(String key) {
    errors.computeIfAbsent(key, k-> new ArrayList<>());
  }

  private void initializeErrorsIfNull() {
    if(errors == null) {
      errors = new HashMap<>();
    }
  }

  // ------------------- static -------------- //

  public static <T> Response<T> ok (T data) {
    Assert.notNull(data, "data should not null");
    return Response.status(HttpStatus.OK, data);
  }

  public static <T> Response<T> okOrNotFound(@Nullable T data) {
    if(Objects.isNull(data)) {
      return Response.status(HttpStatus.NOT_FOUND, null);
    } else {
      return Response.ok(data);
    }
  }

  private static <T> Response<T> status(HttpStatus httpStatus, T data) {
    return Response.<T>builder().code(httpStatus.value()).status(httpStatus.getReasonPhrase())
        .data(data).build();
  }

}
