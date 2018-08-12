package net.hackbee.springboot2.react.exception;

public class ClientNotFoundException extends RuntimeException {

  private ClientNotFoundException() {
  }

  public ClientNotFoundException(Long clientId) {
    super("Did not found Client with clientId =[" + clientId.toString() + "]");
  }
}
