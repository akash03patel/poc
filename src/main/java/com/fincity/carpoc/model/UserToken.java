package com.fincity.carpoc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

/**
 * The type User token.
 */
@Data
@Entity
@Table(name = "user_token")
public class UserToken {

  /**
   * The Id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  /**
   * The Token.
   */
  @Column(name = "token")
  private String token;

  /**
   * The Token type.
   */
  @Column(name = "token_type")
  private int tokenType;

  /**
   * The User.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private Users user;

  /**
   * The Created on.
   */
  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  @Column(name = "created_on")
  private Date createdOn;

}
