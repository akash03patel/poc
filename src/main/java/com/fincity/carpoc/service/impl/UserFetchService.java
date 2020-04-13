package com.fincity.carpoc.service.impl;

import com.fincity.carpoc.dao.UserRepository;
import com.fincity.carpoc.model.Users;
import com.fincity.carpoc.service.IUserFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type User fetch service.
 */
@Service
public class UserFetchService implements IUserFetchService {

  /**
   * UserRepository.
   */
  @Autowired
  private UserRepository userRepository;

  @Override
  public Users fetchUserById(long id) throws Exception {
    try {
      return userRepository.findByUserId(id);
    } catch (Exception ex) {
      throw ex;
    }
  }
}
