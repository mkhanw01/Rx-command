package com.waseem.command.service;

import com.waseem.command.model.service.ServiceRequest;
import reactor.core.publisher.Mono;

/**
 * Created by khan on 12/29/17.
 */
public interface ServiceExecutor {
  <T, R extends ServiceRequest>Mono <T> execute(Class<? extends Command<T, R>> commandClass, R
      request);
}
