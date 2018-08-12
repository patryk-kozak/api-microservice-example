package net.hackbee.springboot2.react.exception;

public class VendorNotFoundException extends RuntimeException {

  private VendorNotFoundException() {
  }

  public VendorNotFoundException(Long vendorId) {
    super("Did not found Vendor with vendorId =[" + vendorId.toString() + "]");
  }

}
