package com.waseem.command.controller;

import com.waseem.command.model.controller.Response;
import com.waseem.command.service.exception.CommandValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.Path;

/**
 * Created by khan on 1/2/18.
 */
@RestControllerAdvice
public class ExceptionController {
  @ExceptionHandler(Throwable.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Response<Object> throwableHandler(Throwable throwable) {
    return Response.status(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage());
  }

  @ExceptionHandler(CommandValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Response<Object> commandValidationHandler(CommandValidationException e) {
    Response<Object> response = Response.status(HttpStatus.BAD_REQUEST, e.getMessage());

    e.getConstraintViolations().forEach(constraintViolation -> {
      for (String s : getAttribute(constraintViolation)) {
        response.addError(s, constraintViolation.getMessage());
      }
    });
    return response;
  }

  private String[] getAttribute(ConstraintViolation<?> constraintViolation) {
    String[] values =
        (String[]) constraintViolation.getConstraintDescriptor().getAttributes().get("path");
    if (values == null || values.length == 0) {
      return getAttributeFromPath(constraintViolation);
    } else {
      return values;
    }
  }

  private String[] getAttributeFromPath(ConstraintViolation<?> constraintViolation) {
    Path path = constraintViolation.getPropertyPath();
    StringBuilder builder = new StringBuilder();
    path.forEach(node -> {
      if (node.getName() != null) {
        if (builder.length() > 0) {
          builder.append(".");
        }
        builder.append(node.getName());
      }
    });
    return new String[] {builder.toString()};
  }
}
