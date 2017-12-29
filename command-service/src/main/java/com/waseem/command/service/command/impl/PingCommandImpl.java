package com.waseem.command.service.command.impl;

import com.waseem.command.model.service.ServiceRequest;
import com.waseem.command.service.AbstractCommand;
import com.waseem.command.service.command.PingCommand;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Created by khan on 12/29/17.
 */
@Component
public class PingCommandImpl extends AbstractCommand<String, ServiceRequest> implements PingCommand {

  @Override
  public Mono<String> doExecute(ServiceRequest request) {
    return Mono.just("ping-pong");
  }
}
