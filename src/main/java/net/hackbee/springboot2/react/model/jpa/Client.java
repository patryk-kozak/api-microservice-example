package net.hackbee.springboot2.react.model.jpa;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long clientId;
  private Long foodStorage;
  private Long funds;

}
