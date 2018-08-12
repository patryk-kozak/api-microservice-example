package net.hackbee.springboot2.react.repository.jpa;

import org.springframework.data.repository.CrudRepository;


import net.hackbee.springboot2.react.model.jpa.CatFood;

public interface CatFoodRepository extends CrudRepository<CatFood, Long> {
}
