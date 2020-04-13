package com.fincity.carpoc.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Post sign up dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostSignUpDto {

  /**
   * The Successfully registered.
   */
  List<UserInfo> successfullyRegistered;

  /**
   * The Already existing.
   */
  List<UserInfo> alreadyExisting;
}
