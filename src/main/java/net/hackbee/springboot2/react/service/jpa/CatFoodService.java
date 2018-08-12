package net.hackbee.springboot2.react.service.jpa;

import net.hackbee.springboot2.react.model.jpa.CatFood;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CatFoodService {
  Mono<CatFood> save(CatFood catFood);

  Mono<CatFood> update(CatFood catFood);

  Mono<CatFood> findByCatFoodId(Long catFoodId);

  Flux<CatFood> findAll();

  Mono<Boolean> delete(Long catFoodId);
}
