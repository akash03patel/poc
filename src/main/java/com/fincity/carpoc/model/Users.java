package com.fincity.carpoc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The type Users.
 */
@Entity
@Table(name = "users")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Users {

  /**
   * The User id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", updatable = false, nullable = false)
  private long userId;

  /**
   * The User name.
   */
  @Column(name = "user_name", nullable = false)
  private String name;

  /**
   * The User email.
   */
  @Column(nullable = false, unique = true)
  private String userEmail;

  /**
   * The Password.
   */
  @Column(nullable = false)
  private String password;

  /**
   * The Created by.
   */
  private long createdBy;
}
