package net.hackbee.springboot2.react.model.mongo;

import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Data;
import net.hackbee.springboot2.react.model.jpa.CatFood;
import net.hackbee.springboot2.react.model.jpa.Client;
import net.hackbee.springboot2.react.model.jpa.Vendor;

@Data
@Document
@AllArgsConstructor
public class Invoice {

  @Id
  private String id;
  private Client client;
  private Vendor vendor;
  private CatFood catFood;
  private int amount;

}
