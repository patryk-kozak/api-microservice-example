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


import net.hackbee.springboot2.react.model.jpa.Vendor;
import net.hackbee.springboot2.react.service.jpa.VendorService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/vendors")
public class VendorsApi {

  @Autowired
  private VendorService vendorService;

  @PostMapping
  public Mono<ResponseEntity<Vendor>> save(@RequestBody Vendor vendor) {
    return this.vendorService.save(vendor)
        .map(savedVendor -> new ResponseEntity<>(savedVendor, HttpStatus.CREATED));
  }

  @PutMapping
  public Mono<ResponseEntity<Vendor>> update(@RequestBody Vendor vendor) {
    return this.vendorService.update(vendor)
        .map(savedVendor -> new ResponseEntity<>(savedVendor, HttpStatus.CREATED))
        .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping
  public Flux<Vendor> findAll() {
    return this.vendorService.findAll();
  }

  @GetMapping("/{id}")
  public Mono<Vendor> findByVendorId(@PathVariable("id") Long vendorId) {
    return this.vendorService.findByVendorId(vendorId);
  }

  @DeleteMapping(path = "/{id}")
  public Mono<ResponseEntity<String>> delete(@PathVariable("id") Long vendorId) {
    return this.vendorService.delete(vendorId).map((Boolean status) ->
        new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED));
  }

}
