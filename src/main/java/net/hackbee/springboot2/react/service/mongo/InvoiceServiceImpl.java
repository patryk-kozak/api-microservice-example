package net.hackbee.springboot2.react.service.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;
import net.hackbee.springboot2.react.model.jpa.CatFood;
import net.hackbee.springboot2.react.model.jpa.Client;
import net.hackbee.springboot2.react.model.jpa.Vendor;
import net.hackbee.springboot2.react.model.mongo.Invoice;
import net.hackbee.springboot2.react.repository.mongo.InvoiceRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class InvoiceServiceImpl implements InvoiceService {

  private final InvoiceRepository invoiceRepository;

  @Override
  public Flux<Invoice> findByClient(Client client) {
    return invoiceRepository.findByClient(client);
  }

  @Override
  public Flux<Invoice> findByVendor(Vendor vendor) {
    return invoiceRepository.findByVendor(vendor);
  }

  @Override
  public Flux<Invoice> findByCatFood(CatFood catFood) {
    return invoiceRepository.findByCatFood(catFood);
  }

  @Override
  public Mono<Invoice> findById(String invoiceId) {
    return invoiceRepository.findById(invoiceId);
  }
}
