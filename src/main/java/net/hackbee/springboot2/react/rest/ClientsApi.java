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


import net.hackbee.springboot2.react.model.jpa.Client;
import net.hackbee.springboot2.react.service.jpa.ClientService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clients")
public class ClientsApi {

  @Autowired
  private ClientService clientService;

  @PostMapping
  public Mono<ResponseEntity<Client>> save(@RequestBody Client client) {
    return this.clientService.save(client)
        .map(savedClient -> new ResponseEntity<>(savedClient, HttpStatus.CREATED));
  }

  @PutMapping
  public Mono<ResponseEntity<Client>> update(@RequestBody Client client) {
    return this.clientService.update(client)
        .map(savedClient -> new ResponseEntity<>(savedClient, HttpStatus.CREATED))
        .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping
  public Flux<Client> findAll() {
    return this.clientService.findAll();
  }

  @GetMapping("/{id}")
  public Mono<Client> findByClientId(@PathVariable("id") Long clientId) {
    return this.clientService.findByClientId(clientId);
  }

  @DeleteMapping(path = "/{id}")
  public Mono<ResponseEntity<String>> delete(@PathVariable("id") Long clientId) {
    return this.clientService.delete(clientId).map((Boolean status) ->
        new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED));
  }

}
