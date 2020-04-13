package com.fincity.carpoc.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.fincity.carpoc.dao.UserRepository;
import com.fincity.carpoc.dao.UserTokenRepository;
import com.fincity.carpoc.model.PostSignInResponse;
import com.fincity.carpoc.model.PostSignUpDto;
import com.fincity.carpoc.model.RegistrationRequest;
import com.fincity.carpoc.model.SignInRequest;
import com.fincity.carpoc.model.TokenPair;
import com.fincity.carpoc.model.UserInfo;
import com.fincity.carpoc.model.Users;
import com.fincity.carpoc.service.IAuthenticationService;
import com.fincity.carpoc.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The type Authentication service.
 */
@Service
public class AuthenticationService implements IAuthenticationService {
  /**
   * BCryptPasswordEncoder.
   */
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  /**
   * UserRepository.
   */
  @Autowired
  UserRepository userRepository;

  /**
   * The Token util.
   */
  @Autowired
  TokenUtil tokenUtil;


  /**
   * The User token repository.
   */
  @Autowired
  private UserTokenRepository userTokenRepository;

  /**
   * LOGGER.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);


  @Override
  public PostSignUpDto doRegistration(List<RegistrationRequest> requestDtoList)
      throws Exception {
    LOGGER.info("inside doRegistration for user Registration");
    try {
      List<String> existingMails = userRepository.findAllUserEmail();
      List<RegistrationRequest> existingUsers = requestDtoList.stream()
          .filter(data -> existingMails.contains(data.getUserEmail())).collect(Collectors.toList());
      requestDtoList.removeAll(existingUsers);
      List<Users> newUsers = requestDtoList.stream().map(data -> {
        final Users user = new Users();
        user.setUserEmail(data.getUserEmail());
        user.setName(data.getUserName());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setCreatedBy(1L);
        return user;
      }).collect(Collectors.toList());
      userRepository.saveAll(newUsers);
      List<UserInfo> registeredMails = requestDtoList.stream().map(data -> new UserInfo(data.getUserName(), data.getUserEmail()))
          .collect(Collectors.toList());
      List<UserInfo> existingUserEmails = existingUsers.stream().map(data -> new UserInfo(data.getUserName(), data.getUserEmail()))
          .collect(Collectors.toList());
      PostSignUpDto postSignUpDto = new PostSignUpDto(registeredMails, existingUserEmails);
      return postSignUpDto;
    } catch (Exception ex) {
      LOGGER.error("Error while registraing users with error message", ex.getMessage());
      throw ex;
    }
  }

  @Override
  public PostSignInResponse doLogin(SignInRequest signInRequest) throws Exception {
    LOGGER.info("inside do login method of Authentication service");
    try {
      Users users = userRepository.findByUserEmail(signInRequest.getUserEmail());
      Optional<Users> optionalUser = Optional.ofNullable(users);
      if (!optionalUser.isPresent()) {
        throw new Exception("No user found with this email");
      } else if (!optionalUser.get().getUserEmail().equalsIgnoreCase(signInRequest.getUserEmail())
          || !passwordEncoder.matches(signInRequest.getPassword(),
          optionalUser.get().getPassword())) {
        throw new Exception("Incorrect Credentials");
      } else {
        final TokenPair tokenPair = tokenUtil.createRefreshAndAccessToken(users);

        PostSignInResponse postSignInResponse = new PostSignInResponse(users.getUserId(),
            users.getName(), users.getUserEmail(), tokenPair);
        return postSignInResponse;
      }
    } catch (Exception ex) {
      LOGGER.error("Error while login with error message {}", ex.getMessage());
      throw ex;
    }
  }
}
