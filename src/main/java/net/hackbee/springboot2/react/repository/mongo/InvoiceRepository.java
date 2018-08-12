package net.hackbee.springboot2.react.repository.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


import net.hackbee.springboot2.react.model.jpa.CatFood;
import net.hackbee.springboot2.react.model.jpa.Client;
import net.hackbee.springboot2.react.model.jpa.Vendor;
import net.hackbee.springboot2.react.model.mongo.Invoice;
import reactor.core.publisher.Flux;

public interface InvoiceRepository extends ReactiveMongoRepository<Invoice, String> {

  Flux<Invoice> findByClient(Client client);

  Flux<Invoice> findByVendor(Vendor vendor);

  Flux<Invoice> findByCatFood(CatFood catFood);

}
