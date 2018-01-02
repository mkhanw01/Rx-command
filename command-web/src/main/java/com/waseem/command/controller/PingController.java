package com.waseem.command.controller;

import com.waseem.command.model.controller.ApiPath;
import com.waseem.command.model.controller.Response;
import com.waseem.command.model.service.PingRequest;
import com.waseem.command.service.ServiceExecutor;
import com.waseem.command.service.command.PingCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Created by khan on 12/30/17.
 */
@RestController
@RequestMapping(value = ApiPath.PING_BASE_PATH)
public class PingController {

  @Value("${server.context-path}")
  private String application;

  @Autowired
  private ServiceExecutor serviceExecutor;


  @GetMapping(value = ApiPath.HOME, produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Response<String>> home() {

    return Mono.just(Response.ok(application)).subscribeOn(Schedulers.elastic());
  }

  private Mono<Response<String>> returnExecutor(PingRequest pingRequest) {

    return this.serviceExecutor.execute(PingCommand.class, pingRequest)
        .map(Response::ok)
        .subscribeOn(Schedulers.elastic());
  }

  @GetMapping(value = ApiPath.PING, produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Response<String>> ping(){
    PingRequest request = PingRequest.builder().build();

    return returnExecutor(request);
  }
}
