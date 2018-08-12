package net.hackbee.springboot2.react.service.jpa;

import net.hackbee.springboot2.react.model.jpa.Vendor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VendorService {
  Mono<Vendor> save(Vendor Vendor);

  Mono<Vendor> update(Vendor Vendor);

  Mono<Vendor> findByVendorId(Long VendorId);

  Flux<Vendor> findAll();

  Mono<Boolean> delete(Long VendorId);
}
