package com.fincity.carpoc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type User info.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

  /**
   * The User name.
   */
  private String userName;

  /**
   * The User email.
   */
  private String userEmail;
}
