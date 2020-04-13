package com.fincity.carpoc.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Post sign in response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostSignInResponse {

  /**
   * The User id.
   */
  private long userId;

  /**
   * The User name.
   */
  private String userName;

  /**
   * The User email.
   */
  private String userEmail;

  /**
   * The Token pair.
   */
  private TokenPair tokenPair;
}
