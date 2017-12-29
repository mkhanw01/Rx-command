package com.waseem.command.service;

import com.waseem.command.model.service.ServiceRequest;
import reactor.core.publisher.Mono;

/**
 * Created by khan on 12/29/17.
 */
public interface Command<RESULT, REQUEST extends ServiceRequest> {
  Mono<RESULT> execute(REQUEST request);
}
