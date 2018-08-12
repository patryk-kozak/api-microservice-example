package net.hackbee.springboot2.react;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class ReactApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReactApplication.class, args);
  }
}
