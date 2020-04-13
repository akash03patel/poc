package com.fincity.carpoc.model;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Sign in request.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {

  /**
   * The User email.
   */
  @NotNull
  private String userEmail;

  /**
   * The Password.
   */
  @NotNull
  private String password;
}
