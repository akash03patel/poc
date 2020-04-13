package com.fincity.carpoc.dao;

import java.util.List;
import com.fincity.carpoc.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * The interface User repository.
 */
public interface UserRepository extends JpaRepository<Users, Long> {

  /**
   * Find by user email users.
   *
   * @param userEmail the user email
   * @return the users
   */
  Users findByUserEmail(String userEmail);

  /**
   * Find by user id users.
   *
   * @param id the id
   * @return the users
   */
  Users findByUserId(long id);

  /**
   * Find all user email list.
   *
   * @return the list
   */
  @Query("select u.userEmail from Users u")
  List<String> findAllUserEmail();

  @Query("from Users u where u.createdBy=:sampleUserId")
  List<Users> getAllRegisteredUsers(long sampleUserId);
}
