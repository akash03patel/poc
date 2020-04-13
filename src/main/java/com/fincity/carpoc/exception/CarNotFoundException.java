package com.fincity.carpoc.exception;

import java.io.Serializable;

/**
 * The type Car not found exception.
 */
public class CarNotFoundException extends RuntimeException implements Serializable {
  private static final long serialVersionUID = -8831435040472055457L;

  /**
   * Instantiates a new Car not found exception.
   *
   * @param message the message
   */
  public CarNotFoundException(final String message) {
    super(message);
  }
}
