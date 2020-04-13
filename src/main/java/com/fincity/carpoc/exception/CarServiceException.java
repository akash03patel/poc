package com.fincity.carpoc.exception;

import java.io.Serializable;
import lombok.Data;

@Data
public class CarServiceException extends RuntimeException implements Serializable {

  private static final long serialVersionUID = -8831435040472055457L;

  /**
   * Instantiates a new Data CarServiceException.
   *
   * @param message the message
   */
  public CarServiceException(final String message) {
    super(message);
  }
}
