package net.hackbee.springboot2.react.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;
import net.hackbee.springboot2.react.config.AsyncJdbcTransactionTemplate;
import net.hackbee.springboot2.react.config.JdbcPoolScheduler;
import net.hackbee.springboot2.react.exception.ClientNotFoundException;
import net.hackbee.springboot2.react.model.jpa.Client;
import net.hackbee.springboot2.react.repository.jpa.ClientRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;

  private final JdbcPoolScheduler jdbcPoolScheduler;

  private final AsyncJdbcTransactionTemplate asyncJdbcTransactionTemplate;

  @Override
  public Mono<Client> save(Client client) {
    return asyncJdbcTransactionTemplate.asyncTx(() -> clientRepository.save(client));
  }

  @Override
  public Mono<Client> update(Client client) {
    Mono<Boolean> booleanMono = asyncJdbcTransactionTemplate.asyncTx(
        () ->
            clientRepository.findById(client.getClientId())
                .map(existingClient -> this.delete(existingClient.getClientId()))
    ).flatMap(test -> test.orElse(Mono.just(Boolean.FALSE)));

    return booleanMono.filter(b -> b).then(this.save(client));
  }

  @Override
  public Mono<Client> findByClientId(Long clientId) {
    return asyncJdbcTransactionTemplate.asyncTx(() -> clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId)));
  }

  @Override
  public Flux<Client> findAll() {
    return asyncJdbcTransactionTemplate.asyncTx(clientRepository::findAll).flatMapIterable(v -> v);
  }

  @Override
  public Mono<Boolean> delete(Long clientId) {
    return this.findByClientId(clientId).doOnNext(clientRepository::delete).thenReturn(Boolean.TRUE);
  }
}
