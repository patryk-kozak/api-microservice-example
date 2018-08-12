package net.hackbee.springboot2.react.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;
import net.hackbee.springboot2.react.config.AsyncJdbcTransactionTemplate;
import net.hackbee.springboot2.react.config.JdbcPoolScheduler;
import net.hackbee.springboot2.react.exception.CatFoodNotFoundException;
import net.hackbee.springboot2.react.model.jpa.CatFood;
import net.hackbee.springboot2.react.repository.jpa.CatFoodRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CatFoodServiceImpl implements CatFoodService {

  private final CatFoodRepository catFoodRepository;

  private final JdbcPoolScheduler jdbcPoolScheduler;

  private final AsyncJdbcTransactionTemplate asyncJdbcTransactionTemplate;

  @Override
  public Mono<CatFood> save(CatFood catFood) {
    return asyncJdbcTransactionTemplate.asyncTx(() -> catFoodRepository.save(catFood));
  }

  @Override
  public Mono<CatFood> update(CatFood catFood) {
    Mono<Boolean> booleanMono = asyncJdbcTransactionTemplate.asyncTx(
        () ->
            catFoodRepository.findById(catFood.getCatFoodId())
                .map(existingcatFood -> this.delete(existingcatFood.getCatFoodId()))
    ).flatMap(test -> test.orElse(Mono.just(Boolean.FALSE)));

    return booleanMono.filter(b -> b).then(this.save(catFood));
  }

  @Override
  public Mono<CatFood> findByCatFoodId(Long catFoodId) {
    return asyncJdbcTransactionTemplate
        .asyncTx(() -> catFoodRepository.findById(catFoodId).orElseThrow(() -> new CatFoodNotFoundException(catFoodId)));
  }

  @Override
  public Flux<CatFood> findAll() {
    return asyncJdbcTransactionTemplate.asyncTx(catFoodRepository::findAll).flatMapIterable(v -> v);
  }

  @Override
  public Mono<Boolean> delete(Long catFoodId) {
    return this.findByCatFoodId(catFoodId).doOnNext(catFoodRepository::delete).thenReturn(Boolean.TRUE);
  }

}
