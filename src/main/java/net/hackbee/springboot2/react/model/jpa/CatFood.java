package net.hackbee.springboot2.react.model.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class CatFood {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long catFoodId;
  private String name;
  private Double price;

}
