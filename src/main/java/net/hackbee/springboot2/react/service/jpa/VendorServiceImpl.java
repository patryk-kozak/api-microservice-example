package net.hackbee.springboot2.react.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;
import net.hackbee.springboot2.react.config.AsyncJdbcTransactionTemplate;
import net.hackbee.springboot2.react.config.JdbcPoolScheduler;
import net.hackbee.springboot2.react.exception.VendorNotFoundException;
import net.hackbee.springboot2.react.model.jpa.Vendor;
import net.hackbee.springboot2.react.repository.jpa.VendorRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class VendorServiceImpl implements VendorService {

  private final VendorRepository vendorRepository;

  private final JdbcPoolScheduler jdbcPoolScheduler;

  private final AsyncJdbcTransactionTemplate asyncJdbcTransactionTemplate;

  @Override
  public Mono<Vendor> save(Vendor vendor) {
    return asyncJdbcTransactionTemplate.asyncTx(() -> vendorRepository.save(vendor));
  }

  @Override
  public Mono<Vendor> update(Vendor vendor) {
    Mono<Boolean> booleanMono = asyncJdbcTransactionTemplate.asyncTx(
        () ->
            vendorRepository.findById(vendor.getVendorId())
                .map(existingVendor -> this.delete(existingVendor.getVendorId()))
    ).flatMap(test -> test.orElse(Mono.just(Boolean.FALSE)));

    return booleanMono.filter(b -> b).then(this.save(vendor));
  }

  @Override
  public Mono<Vendor> findByVendorId(Long vendorId) {
    return asyncJdbcTransactionTemplate
        .asyncTx(() -> vendorRepository.findById(vendorId).orElseThrow(() -> new VendorNotFoundException(vendorId)));
  }

  @Override
  public Flux<Vendor> findAll() {
    return asyncJdbcTransactionTemplate.asyncTx(vendorRepository::findAll).flatMapIterable(v -> v);
  }

  @Override
  public Mono<Boolean> delete(Long vendorId) {
    return this.findByVendorId(vendorId).doOnNext(vendorRepository::delete).thenReturn(Boolean.TRUE);
  }

}
