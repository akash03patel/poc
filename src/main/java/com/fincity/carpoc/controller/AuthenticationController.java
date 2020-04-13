package com.fincity.carpoc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import com.fincity.carpoc.model.PostSignUpDto;
import com.fincity.carpoc.model.RegistrationRequest;
import com.fincity.carpoc.model.SignInRequest;
import com.fincity.carpoc.service.IAuthenticationService;
import com.fincity.carpoc.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller for all the authentication APIs.
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

  /**
   * LOGGER.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

  /**
   * Authentication Service.
   */
  @Autowired
  private IAuthenticationService authenticationService;

  @Autowired
  TokenUtil tokenUtil;

  /**
   * Registers the user.
   *
   * @param registrationRequest the registration request.
   * @return success or failure message.
   */
  @PostMapping("/register")
  public ResponseEntity doRegistration(
      @RequestBody @Valid List<RegistrationRequest> registrationRequest,
      HttpServletRequest httpServletRequest) throws Exception {
    LOGGER.info("inside do Registartion method of Authentication Controller for user "
        + "registration ");
    PostSignUpDto postSignUpDto = authenticationService.doRegistration(registrationRequest);
    return new ResponseEntity(postSignUpDto, HttpStatus.OK);
  }

  /**
   * method to login user.
   *
   * @param signInRequest signin params.
   * @return postsignresponse. response entity
   */
  @PostMapping("/login")
  public ResponseEntity doLogin(@RequestBody @Valid final SignInRequest signInRequest) throws Exception {
    LOGGER.info("inside do login method of authentication controller for login");
    return new ResponseEntity(authenticationService.doLogin(signInRequest), HttpStatus.OK);
  }
}
