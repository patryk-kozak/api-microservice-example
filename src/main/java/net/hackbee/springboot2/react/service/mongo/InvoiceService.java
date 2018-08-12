package net.hackbee.springboot2.react.service.mongo;

import net.hackbee.springboot2.react.model.jpa.CatFood;
import net.hackbee.springboot2.react.model.jpa.Client;
import net.hackbee.springboot2.react.model.jpa.Vendor;
import net.hackbee.springboot2.react.model.mongo.Invoice;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InvoiceService {

  Flux<Invoice> findByClient(Client client);

  Flux<Invoice> findByVendor(Vendor vendor);

  Flux<Invoice> findByCatFood(CatFood catFood);

  Mono<Invoice> findById(String invoiceId);

}
