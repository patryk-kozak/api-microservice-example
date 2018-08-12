package net.hackbee.springboot2.react.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import net.hackbee.springboot2.react.model.jpa.CatFood;
import net.hackbee.springboot2.react.service.jpa.CatFoodService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/catFoods")
public class CatFoodApi {

  @Autowired
  private CatFoodService catFoodService;

  @PostMapping
  public Mono<ResponseEntity<CatFood>> save(@RequestBody CatFood catFood) {
    return this.catFoodService.save(catFood)
        .map(savedCatFood -> new ResponseEntity<>(savedCatFood, HttpStatus.CREATED));
  }

  @PutMapping
  public Mono<ResponseEntity<CatFood>> update(@RequestBody CatFood catFood) {
    return this.catFoodService.update(catFood)
        .map(savedCatFood -> new ResponseEntity<>(savedCatFood, HttpStatus.CREATED))
        .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping
  public Flux<CatFood> findAll() {
    return this.catFoodService.findAll();
  }

  @GetMapping("/{id}")
  public Mono<CatFood> findByCatFoodId(@PathVariable("id") Long catFoodId) {
    return this.catFoodService.findByCatFoodId(catFoodId);
  }

  @DeleteMapping(path = "/{id}")
  public Mono<ResponseEntity<String>> delete(@PathVariable("id") Long catFoodId) {
    return this.catFoodService.delete(catFoodId).map((Boolean status) ->
        new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED));
  }

}
