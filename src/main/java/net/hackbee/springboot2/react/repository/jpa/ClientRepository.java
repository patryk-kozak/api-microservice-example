package net.hackbee.springboot2.react.repository.jpa;

import org.springframework.data.repository.CrudRepository;


import net.hackbee.springboot2.react.model.jpa.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
