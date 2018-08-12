package net.hackbee.springboot2.react.exception;

public class CatFoodNotFoundException extends RuntimeException {

  private CatFoodNotFoundException() {
  }

  public CatFoodNotFoundException(Long catFoodId) {
    super("Did not found CatFood with catFoodId =[" + catFoodId.toString() + "]");
  }
}
