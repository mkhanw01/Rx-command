package com.waseem.command.service.impl;


import com.waseem.command.model.service.ServiceRequest;
import com.waseem.command.service.Command;
import com.waseem.command.service.ServiceExecutor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import reactor.core.publisher.Mono;

/**
 * Created by khan on 12/29/17.
 */
public class ServiceExecutorImpl implements ServiceExecutor, ApplicationContextAware {

  private ApplicationContext applicationContext;

  @Override
  public <T, R extends ServiceRequest> Mono<T> execute(Class<? extends Command<T, R>> commandClass,
      R request) {
    Command<T, R> command = applicationContext.getBean(commandClass);
    return command.execute(request);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
