package com.fincity.carpoc.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Registration request.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

  /**
   * The User name.
   */
  @NotNull
  private String userName;

  /**
   * The User email.
   */
  @NotNull
  @Email
  private String userEmail;

  /**
   * The Password.
   */
  @NotNull
  @Size(min = 8)
  private String password;
}
