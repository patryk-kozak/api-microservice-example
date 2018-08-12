package net.hackbee.springboot2.react.service.jpa;

import net.hackbee.springboot2.react.model.jpa.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

  Mono<Client> save(Client client);

  Mono<Client> update(Client client);

  Mono<Client> findByClientId(Long clientId);

  Flux<Client> findAll();

  Mono<Boolean> delete(Long clientId);

}
