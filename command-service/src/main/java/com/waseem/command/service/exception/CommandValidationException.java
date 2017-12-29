package com.waseem.command.service.exception;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Created by khan on 12/29/17.
 */
public class CommandValidationException extends RuntimeException {

  private Set<ConstraintViolation<?>> constraintViolations;

  @SuppressWarnings("unchecked")
  public CommandValidationException(Set constraintViolations) {
    this.constraintViolations = (Set<ConstraintViolation<?>>) constraintViolations;
  }

  public Set<ConstraintViolation<?>> getConstraintViolations() {
    return constraintViolations;
  }
}
