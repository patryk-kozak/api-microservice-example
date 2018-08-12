package net.hackbee.springboot2.react.config;

import java.util.function.Supplier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;


import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class AsyncJdbcTransactionTemplate {

  private final TransactionTemplate txTemplate;

  public AsyncJdbcTransactionTemplate(TransactionTemplate txTemplate) {
    this.txTemplate = txTemplate;
  }

  public <T> Mono<T> asyncTx(Supplier<T> s) {
    return Mono.fromCallable(() -> txTemplate.execute(status -> s.get()))
        .publishOn(Schedulers.elastic())
        .publishOn(Schedulers.parallel());
  }
}
