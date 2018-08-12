package net.hackbee.springboot2.react.config;

import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Configuration
public class JdbcPoolScheduler {

  private final Integer maxPoolSize;

  public JdbcPoolScheduler(@Value("${spring.datasource.maximum-pool-size}") Integer maxPoolSize) {
    this.maxPoolSize = maxPoolSize;
  }

  @Bean
  public Scheduler jdbcScheduler() {
    return Schedulers.fromExecutor(Executors.newFixedThreadPool(maxPoolSize));
  }

}
