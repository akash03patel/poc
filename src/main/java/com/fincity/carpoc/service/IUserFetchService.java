package com.fincity.carpoc.service;


import com.fincity.carpoc.model.Users;

/**
 * The interface User fetch service.
 */
public interface IUserFetchService {

  /**
   * Fetch user by id users.
   *
   * @param id the id
   * @return the users
   * @throws Exception the exception
   */
  Users fetchUserById(long id) throws Exception;
}
