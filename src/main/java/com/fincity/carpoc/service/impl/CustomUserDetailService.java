package com.fincity.carpoc.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.fincity.carpoc.dao.UserRepository;
import com.fincity.carpoc.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom user detail service.
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

  /**
   * The User repository.
   */
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    final Users user = userRepository.findByUserId(Long.parseLong(username));
    if (Optional.ofNullable(user).isPresent()) {
      final Set<SimpleGrantedAuthority> roleNames = new HashSet<>();
      return new org.springframework.security.core.userdetails.User(
          String.valueOf(user.getUserId()), user.getPassword(), roleNames);
    } else {
      throw new UsernameNotFoundException("No user present with username: " + username);
    }
  }
}
