package com.fincity.carpoc.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
 * The type Custom error response.
 */
@Data
@Builder
public class CustomErrorResponse {

  /**
   * Status of Response.
   */
  private int status;

  /**
   * Error.
   */
  private String error;

  /**
   * Time Stamp.
   */
  private LocalDateTime timeStamp;
}
