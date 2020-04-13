package com.fincity.carpoc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The type Token pair.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TokenPair {

  /**
   * The Access token.
   */
  private String accessToken;

  /**
   * The Refresh token.
   */
  private String refreshToken;
}
